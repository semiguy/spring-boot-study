package com.cbcho.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/auth")
public class LoginController {
	
	@RequestMapping("/login")
	public String loginForm(String error, String logout, Model model) {
		
		log.info("loginForm()...");
		
		if(error != null) {
			log.info("loginForm() : error");
			model.addAttribute("error", "Login Error!!!");
			
		}
		
		if(logout != null) {
			log.info("loginForm() : logout");
			model.addAttribute("logout", "Logout!!!");
			
		}
		
		return "auth/loginForm";
	}
	
	@RequestMapping("/logout")
	public String logoutForm() {
		
		return "auth/logoutForm";
	}
}
