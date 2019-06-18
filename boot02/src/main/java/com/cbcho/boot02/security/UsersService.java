package com.cbcho.boot02.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cbcho.boot02.repository.MemberRepository;

import lombok.extern.java.Log;

@Log
@Service
public class UsersService implements UserDetailsService {
	
	@Autowired
	MemberRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		/*
		User sampleUser = new User(username, "{noop}1111", Arrays.asList(new SimpleGrantedAuthority("ROLE_MANAGER")));
		
		return sampleUser;
		*/
		/*
		repo.findById(username)
			.ifPresent(member -> log.info("" + member));
		
		return null;
		*/
		return repo.findById(username)
				.filter(m -> m != null)
				.map(m -> new SecurityUser(m)).get(); 
	}	
}
