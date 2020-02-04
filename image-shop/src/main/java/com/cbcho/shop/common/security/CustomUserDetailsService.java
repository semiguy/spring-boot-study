package com.cbcho.shop.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cbcho.shop.common.security.domain.CustomUser;
import com.cbcho.shop.domain.Member;
import com.cbcho.shop.mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// userName은 사용자아이디를 의미한다.
		log.warn("Load User By UserName : " + username); 
		
		Member member = memberMapper.readByUserId(username);
		
		log.warn("queried by member mapper : " + member);
		
		return member == null ? null : new CustomUser(member); 
	}
}
