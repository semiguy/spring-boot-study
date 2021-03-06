package com.cbcho.shop.common.security.domain;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.cbcho.shop.domain.Member;

public class CustomUser extends User {
	
	private Member member;
	
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		
		super(username, password, authorities);
	}
	
	public CustomUser(Member member) {
		
		super(member.getUserId(), member.getUserPw(), member.getAuthList().stream()
				.map(auth -> new SimpleGrantedAuthority(auth.getAuth())).collect(Collectors.toList()));
		
		this.member = member;
	}
	
	public Member getMember() {
		
		return member;
	}
}
