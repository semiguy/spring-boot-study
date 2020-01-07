package com.cbcho.mybatis.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.cbcho.mybatis.domain.Member;
import com.cbcho.mybatis.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/users")
public class MemberController {
	
	private final MemberService memberService;
	
	@Autowired
	public MemberController(MemberService memberService) {
		
		this.memberService = memberService;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Void> register(@Validated @RequestBody Member member, UriComponentsBuilder uriBuilder) throws Exception {
		
		log.info("register()...");
		
		memberService.register(member);
		
		log.info("register member.getUserNo()=" + member.getUserNo()); 
		
		URI resourceUri = uriBuilder.path("users/{userNo}")
				.buildAndExpand(member.getUserNo())
				.encode()
				.toUri();
		
		return ResponseEntity.created(resourceUri).build();
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Member>> list() throws Exception {
		
		log.info("list()...");
		
		return new ResponseEntity<List<Member>>(memberService.list(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{userNo}", method = RequestMethod.GET)
	public ResponseEntity<Member> read(@PathVariable("userNo")Integer userNo) throws Exception {
		
		log.info("read()...");
		
		Member member = memberService.read(userNo);
		
		return new ResponseEntity<Member>(member, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{userNo}", method = RequestMethod.PUT)
	public ResponseEntity<Void> modify(@PathVariable("userNo") Integer userNo, @Validated @RequestBody Member member) throws Exception {
		
		log.info("modify()...");
		
		member.setUserNo(userNo);
		memberService.modify(member);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/{userNo}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> remove(@PathVariable("userNo") Integer userNo) throws Exception {
		
		log.info("remove()...");
		
		memberService.remove(userNo);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
	}
	
}
