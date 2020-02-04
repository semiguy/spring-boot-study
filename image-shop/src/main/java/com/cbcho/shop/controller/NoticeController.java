package com.cbcho.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cbcho.shop.domain.Notice;
import com.cbcho.shop.service.NoticeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	private final NoticeService noticeService;
	
	@Autowired
	public NoticeController(NoticeService noticeService) {
		
		this.noticeService = noticeService;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void registerForm(Model model) throws Exception {
		
		log.info("registerForm()...");
		
		Notice notice = new Notice();
		
		model.addAttribute(notice);
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String register(Notice notice, RedirectAttributes rttr) throws Exception {
		
		log.info("register()...");
		
		noticeService.register(notice);
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/notice/list";
	}
		
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) throws Exception {
		
		log.info("list()...");
		
		model.addAttribute("list", noticeService.list());
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(int noticeNo, Model model) throws Exception {
		
		log.info("read()...");
		
		model.addAttribute(noticeService.read(noticeNo)); 
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void modifyForm(int noticeNo, Model model) throws Exception {
		
		log.info("modifyForm()...");
		
		model.addAttribute(noticeService.read(noticeNo));
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String modify(Notice notice, RedirectAttributes rttr) throws Exception {
		
		log.info("modify()...");
		
		noticeService.modify(notice);
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/notice/list";
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String remove(int noticeNo, RedirectAttributes rttr) throws Exception {
		
		log.info("remove()...");
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/notice/list";
	}
}
