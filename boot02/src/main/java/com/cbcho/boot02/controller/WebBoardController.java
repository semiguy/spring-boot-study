package com.cbcho.boot02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cbcho.boot02.model.WebBoard;
import com.cbcho.boot02.repository.WebBoardRepository;
import com.cbcho.boot02.vo.PageMaker;
import com.cbcho.boot02.vo.PageVO;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/boards/")
@Log
public class WebBoardController {
	
	@Autowired
	private WebBoardRepository repo;
	
	/*
	@GetMapping("/list")
	public void list() {
		log.info("list() called..");
	}
	*/
	
	/*
	@GetMapping("/list")
	public void list(@PageableDefault(
			direction = Sort.Direction.DESC, 
			sort = "bno", size = 10, page = 0) Pageable page) {
		
		log.info("list() called..." + page);
	}
	*/
	
	
	@GetMapping("/list")
	public void list(PageVO vo, Model model) {
		
		Pageable page = vo.makePageable(0, "bno");
		
		Page<WebBoard> result = null;
		String searchType = vo.getType();
		String keyword = vo.getKeyword();
		
		
		if(!StringUtils.isEmpty(searchType) && !StringUtils.isEmpty(keyword)) {
			
			log.info("searchType : " + searchType + " / keyword : " + keyword); 
			
			switch (searchType) {
			case "t":
			{			
				result = repo.findByTitleContainingAndBnoGreaterThan(keyword, 0L, page);
			}
			break;
			
			case "c":
			{			
				result = repo.findByContentContainingAndBnoGreaterThan(keyword, 0L, page);
			}
			break;	
			
			case "w":
			{			
				result = repo.findByWriterContainingAndBnoGreaterThan(keyword, 0L, page);
			}
			break;
			
			default:
				log.info("check searchType !!!");
				break;
			}
		} else {
			
			result = repo.findAll(page);
		}
		
			
		log.info("" + page); 
		log.info("" + result); 
		
		log.info("TOTAL PAGE NUMBER: " + result.getTotalPages()); 
		
		model.addAttribute("result", new PageMaker(result)); 
	}
	
	@GetMapping("/register")
	public void registerGET(@ModelAttribute("vo")WebBoard vo) {
		log.info("register get"); 
	}
	
	
	@PostMapping("/register")
	public String registerPOST(@ModelAttribute("vo")WebBoard vo, RedirectAttributes rttr) {
		
		log.info("register post");
		log.info("" + vo); 
		
		repo.save(vo);
		rttr.addFlashAttribute("msg", "success");
		
		return "redirect:/boards/list";
	}
	
	@GetMapping("/view")
	public void view(Long bno, @ModelAttribute("pageVO") PageVO vo, Model model) {
		
		log.info("BNO: " + bno);
		
		repo.findById(bno).ifPresent(board -> model.addAttribute("vo", board)); 
	}
	
	@Secured(value = {"ROLE_BASIC", "ROLE_MANAGER", "ROLE_ADMIN"})
	@GetMapping("/modify")
	public void modify(Long bno, @ModelAttribute("pageVO") PageVO vo, Model model) {
		
		log.info("MODIFY BNO: " + bno);
		
		repo.findById(bno).ifPresent(board -> model.addAttribute("vo", board)); 
	}
	
	@Secured(value = {"ROLE_BASIC", "ROLE_MANAGER", "ROLE_ADMIN"})
	@PostMapping("/modify")
	public String modifyPost(WebBoard board, PageVO vo, RedirectAttributes rttr) {
		
		log.info("Modify WebBoard: " + board); 
		
		repo.findById(board.getBno()).ifPresent(origin -> {
			origin.setTitle(board.getTitle());
			origin.setContent(board.getContent());
			
			repo.save(origin);
			
			rttr.addFlashAttribute("msg", "success");
			rttr.addAttribute("bno", origin.getBno());
		});
		
		// 페이징과 검색했던 결과로 이동하는 경우
		rttr.addAttribute("page", vo.getPage());
		rttr.addAttribute("size", vo.getSize());
		rttr.addAttribute("type", vo.getType());
		rttr.addAttribute("keyword", vo.getKeyword());
		
		return "redirect:/boards/view";
	}
	
	@Secured(value = {"ROLE_BASIC", "ROLE_MANAGER", "ROLE_ADMIN"})
	@PostMapping("/delete")
	public String delete(Long bno, PageVO vo, RedirectAttributes rttr) {
		
		log.info("DELETE BNO: " + bno);
		
		repo.deleteById(bno);
		
		rttr.addFlashAttribute("msg", "success");
		// 페이징과 검색했던 결과로 이동하는 경우
		rttr.addAttribute("page", vo.getPage());
		rttr.addAttribute("size", vo.getSize());
		rttr.addAttribute("type", vo.getType());
		rttr.addAttribute("keyword", vo.getKeyword());
		
		return "redirect:/boards/list";
	}
	

}
