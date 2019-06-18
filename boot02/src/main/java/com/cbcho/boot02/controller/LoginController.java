package com.cbcho.boot02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.java.Log;

@Log
@Controller
public class LoginController {
	
	@GetMapping("/login")
	public void login() {
		log.info("login()...");
	}
	
	@GetMapping("/accessDenied")
	public void accessDenied() {
		log.info("accessDenied()...");
	}
	
	@GetMapping("/logout")
	public void logout() {
		log.info("logout()...");
	}
}
