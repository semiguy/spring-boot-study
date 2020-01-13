package com.cbcho.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
	
	@RequestMapping("/list")
	public void list() {
		log.info("list : access to all");
	}
	
	@RequestMapping("/register")
	public void registerForm() {
		log.info("registerForm : access to member"); 
	}
}
