package com.cbcho.shop.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cbcho.shop.common.security.domain.CustomUser;
import com.cbcho.shop.domain.Item;
import com.cbcho.shop.domain.Member;
import com.cbcho.shop.service.ItemService;
import com.cbcho.shop.service.MemberService;
import com.cbcho.shop.service.UserItemService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/item")
public class ItemController {
	
	private final ItemService itemService;
	private final MemberService memberService;
	private final UserItemService userItemService;
	private final MessageSource messageSource;
	
	@Autowired
	public ItemController(ItemService itemService, MemberService memberService, 
			UserItemService userItemService, MessageSource messageSource) {
		
		this.itemService = itemService;
		this.memberService = memberService;
		this.userItemService = userItemService;
		this.messageSource = messageSource;
	}
	
	@Value("${upload.path}")
	private String uploadPath;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String registerForm(Model model) {
		
		log.info("registerForm()...");
		
		model.addAttribute(new Item());
		
		return "item/register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String register(Item item, RedirectAttributes rttr) throws Exception {
		
		log.info("register()...");
		
		MultipartFile pictureFile = item.getPicture();
		MultipartFile previewFile = item.getPreview();
		
		String createdPictureFilename = uploadFile(pictureFile.getOriginalFilename(), pictureFile.getBytes());
		String createdPreviewFilename = uploadFile(previewFile.getOriginalFilename(), previewFile.getBytes());
		
		item.setPictureUrl(createdPictureFilename);
		item.setPreviewUrl(createdPreviewFilename);
		
		itemService.register(item);
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/item/list";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) throws Exception {
		
		log.info("list()...");
		
		List<Item> itemList = itemService.list();
		
		model.addAttribute("itemList", itemList);
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(Integer itemId, Model model) throws Exception {
		
		log.info("read()...");
		
		Item item = itemService.read(itemId);
		
		model.addAttribute(item);
		
		return "item/read";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String modifyForm(Integer itemId, Model model) throws Exception {
		
		log.info("modifyForm()...");
		
		Item item = itemService.read(itemId);
		
		model.addAttribute(item);
		
		return "item/modify";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String modify(Item item, RedirectAttributes rttr) throws Exception {
		
		log.info("modify()...");
		
		MultipartFile pictureFile = item.getPicture();
		
		if(pictureFile != null && pictureFile.getSize() > 0) {
			String createdFilename = uploadFile(pictureFile.getOriginalFilename(), pictureFile.getBytes());
			
			item.setPictureUrl(createdFilename);
		}
		
		MultipartFile previewFile = item.getPreview();
		
		if(previewFile != null && previewFile.getSize() > 0) {
			String createdFilename = uploadFile(previewFile.getOriginalFilename(), previewFile.getBytes());
			
			item.setPreviewUrl(createdFilename);
		}
		
		itemService.modify(item);
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/item/list";
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String removeForm(Integer itemId, Model model) throws Exception {
		
		log.info("removeForm()...");
		
		Item item = itemService.read(itemId);
		
		model.addAttribute(item);
		
		return "item/remove";
	}
	
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String remove(Item item, RedirectAttributes rttr) throws Exception {
		
		log.info("remove()...");
		
		itemService.remove(item.getItemId());
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/item/list";
	}
	
	@ResponseBody
	@RequestMapping("/display")
	public ResponseEntity<byte[]> displayFile(Integer itemId) throws Exception {
		
		log.info("displayFile()...");
		
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		String fileName = itemService.getPreview(itemId);
		
		try {
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
			
			MediaType mType = getMediaType(formatName);
			
			HttpHeaders headers = new HttpHeaders();
			
			in = new FileInputStream(uploadPath + File.separator + fileName);
			
			if(mType != null) {
				headers.setContentType(mType);
			}
			
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		
		return entity;
	}
	
	@ResponseBody
	@RequestMapping("/download")
	public ResponseEntity<byte[]> downloadFile(Integer itemId, Authentication authentication) throws Exception {
		
		log.info("downloadFile()...");
		
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		String fullName = itemService.getPicture(itemId);
		
		try {
			HttpHeaders headers = new HttpHeaders();
			
			in = new FileInputStream(uploadPath + File.separator + fullName);
			
			String fileName = fullName.substring(fullName.indexOf("_") + 1);
			
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); 
			headers.add("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\""); 
			
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		
		return entity;
	}
	
	// 상품구매요청처리
	@RequestMapping(value = "/buy", method = RequestMethod.POST)
	public String buy(Integer itemId, RedirectAttributes rttr, Authentication authentication) throws Exception {
		
		log.info("buy()...");
		
		CustomUser customUser = (CustomUser)authentication.getPrincipal();
		Member member = customUser.getMember();
		
		int userNo = member.getUserNo();
		
		member.setCoin(memberService.getCoin(userNo));
		
		Item item = itemService.read(itemId);
		
		userItemService.register(member, item);
		
		String message = messageSource.getMessage("item.purchaseComplete", null, Locale.KOREAN); 
		
		rttr.addFlashAttribute("msg", message);
		
		return "redirect:/item/success";
		
	}
	
	// 상품구매성공화면표시
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String success() throws Exception {
		
		log.info("success()...");
		
		return "item/success";
	}
	
	////////////////////////////////////////////////////////////////////////////
	private String uploadFile(String originalName, byte[] fileData) throws Exception {
		
		UUID uid = UUID.randomUUID();
		
		String createdFileName = uid.toString() + "_" + originalName;
		
		File target = new File(uploadPath, createdFileName);
		
		FileCopyUtils.copy(fileData, target);
		
		return createdFileName;
	}
	
	private MediaType getMediaType(String formatName) {
		
		if(formatName != null) {
			if(formatName.equals("JPG") || formatName.equals("jpg")) {
				return MediaType.IMAGE_JPEG;
			}
			
			if(formatName.equals("GIF") || formatName.equals("gif")) {
				return MediaType.IMAGE_GIF;
			}
			
			if(formatName.equals("PNG") || formatName.equals("png")) {
				return MediaType.IMAGE_PNG;
			}
		}
		
		return null;
	}
}
