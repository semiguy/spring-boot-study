package com.cbcho.mybatis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	
	@RequestMapping(value = "/ajaxHome", method = RequestMethod.GET)
	public String ajaxHome() throws Exception {
		
		log.info("ajaxHome()...");
		
		return "ajaxHome";
	}
	
	@RequestMapping(value = "/ajaxHome2", method = RequestMethod.GET)
	public String ajaxHome2() throws Exception {
		
		log.info("ajaxHome2()...");
		
		return "ajaxHome2";
	}
	
	@RequestMapping(value = "/ajaxHome3", method = RequestMethod.GET)
	public String ajaxHome3() throws Exception {
		
		log.info("ajaxHome3()...");
		
		return "ajaxHome3";
	}
}
