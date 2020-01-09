package com.cbcho.mybatis.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cbcho.mybatis.domain.MemberLogin;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/users")
public class LoginController {
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> login(@RequestBody MemberLogin member) {
		
		log.info("login()...");
		log.info("login userId = " + member.getUserId());
		log.info("login userPw = " + member.getUserPw());
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
}
