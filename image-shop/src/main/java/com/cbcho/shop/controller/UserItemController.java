package com.cbcho.shop.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbcho.shop.common.security.domain.CustomUser;
import com.cbcho.shop.domain.Member;
import com.cbcho.shop.domain.UserItem;
import com.cbcho.shop.exception.NotMyItemException;
import com.cbcho.shop.service.UserItemService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/useritem")
public class UserItemController {
	
	private UserItemService userItemService;
	
	@Autowired
	public UserItemController(UserItemService userItemService) {
		
		this.userItemService = userItemService;
	}
	
	@Value("${upload.path}")
	private String uploadPath;
	
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')")
	public void listAll(Model model) throws Exception {
		
		log.info("listAll()...");
		model.addAttribute("list", userItemService.listAll());
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')")
	public void list(Model model, Authentication authentication) throws Exception {
		
		log.info("list()...");
		
		CustomUser customUser = (CustomUser)authentication.getPrincipal();
		Member member = customUser.getMember();
		
		int userNo = member.getUserNo();
		
		model.addAttribute("list", userItemService.list(userNo));
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')")
	public void read(int userItemNo, Model model) throws Exception {
		
		model.addAttribute(userItemService.read(userItemNo)); 
	}
	
	// 구매상품다운로드
	@ResponseBody
	@RequestMapping("/download")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')")
	public ResponseEntity<byte[]> download(int userItemNo, Authentication authentication) throws Exception {
		
		log.info("download()...");
		
		UserItem userItem = userItemService.read(userItemNo);
		
		// 구매한 상품이 사용자의 것인지 체크
		CustomUser customUser = (CustomUser)authentication.getPrincipal();
		Member member = customUser.getMember();
		
		if(userItem.getUserNo() != member.getUserNo()) {
			throw new NotMyItemException("It is Not My Item.");
		}
		
		String fullName = userItem.getPictureUrl();
		
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
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
	
	// 본인이 구입한 상품이 아닌 메시지를 보여주는 화면을 생성
	@RequestMapping(value = "/notMyItem", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	public void notMyItem(Model model) throws Exception {
		
	}
}
