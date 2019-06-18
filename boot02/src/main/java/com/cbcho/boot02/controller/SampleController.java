package com.cbcho.boot02.controller;

import java.util.Arrays;
import java.util.Date;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.java.Log;

@Log
@Controller
public class SampleController {
	
	@GetMapping("/")
	public String index() {
		log.info("index");
		
		return "index";
	}
	
	@GetMapping("/guest")
	public void forGuest() {
		log.info("guest");
	}
	
	@GetMapping("/manager")
	public void forManager() {
		log.info("manager");
	}
	
	@GetMapping("/admin")
	public void forAdmin() {
		log.info("admin");
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping("/adminSecret")
	public void forAdminSecret() {
		log.info("admin secret");
	}
	
	@Secured({"ROLE_MANAGER"})
	@RequestMapping("/managerSecret")
	public void forManagerSecret() {
		log.info("manager secret");
	}
	
	@GetMapping("/sample1")
	public void sample1(Model model) {
		
		//model.addAttribute("greeting", "Hello World");
		model.addAttribute("greeting", "안녕하세요.");
	}
	
	@GetMapping("/sample7")
	public void sample7(Model model) {
		model.addAttribute("now", new Date());
		model.addAttribute("price", 123456789);
		model.addAttribute("title", "This is a just sample.");
		model.addAttribute("options", Arrays.asList("AAA", "BBB", "CCC", "DDD"));
	}
	
	@GetMapping("/sample/hello")
	public void hello() {
		
	}
}
