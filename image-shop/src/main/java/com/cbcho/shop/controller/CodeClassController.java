package com.cbcho.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cbcho.shop.domain.CodeClass;
import com.cbcho.shop.service.CodeClassService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/codeclass")
//관리자 권한을 가진 사용자만 접근이 가능하다.
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class CodeClassController {
	
	private final CodeClassService codeClassService;
	
	@Autowired
	public CodeClassController(CodeClassService codeClassService) {
		
		this.codeClassService = codeClassService;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerForm(Model model) throws Exception {
		
		log.info("registerForm()...");
		
		CodeClass codeClass = new CodeClass();
		
		model.addAttribute(codeClass);
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(CodeClass codeClass, RedirectAttributes rttr) throws Exception {
		
		log.info("register()...");
		
		codeClassService.register(codeClass);
		
		rttr.addFlashAttribute("mag", "SUCCESS");
		
		return "redirect:/codeclass/list";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) throws Exception {
		
		log.info("list()...");
		
		model.addAttribute("list", codeClassService.list());
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(String classCode, Model model) throws Exception {
		
		log.info("read()...");
		model.addAttribute(codeClassService.read(classCode)); 
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyForm(String classCode, Model model) throws Exception {
		
		log.info("modifyForm()...");
		model.addAttribute(codeClassService.read(classCode));
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(CodeClass codeClass, RedirectAttributes rttr) throws Exception {
		
		log.info("modify()...");
		
		codeClassService.modify(codeClass);
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/codeclass/list";
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(String classCode, RedirectAttributes rttr) throws Exception {
		
		log.info("remove()...");
		
		codeClassService.remove(classCode);
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/codeclass/list";
	}
}
