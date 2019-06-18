package com.cbcho.boot02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.cbcho.boot02.model.Member;
import com.cbcho.boot02.model.MemberRole;
import com.cbcho.boot02.repository.MemberRepository;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class MemberTests {
	
	@Autowired
	private MemberRepository repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void testInsert() {
		
		String encryptPassword = "";
		
		for(int i=0; i <= 100; i++) {
			
			Member member = new Member();
			member.setUid("user" + i);
			
			encryptPassword = passwordEncoder.encode("pwd" + i);
			member.setUpw(encryptPassword);
			member.setUname("사용자" + i);
			
			MemberRole role = new MemberRole();
			if(i <= 80) {
				role.setRoleName("BASIC");
			} else if(i <= 90) {
				role.setRoleName("MANAGER");
			} else {
				role.setRoleName("ADMIN");
			}
			
			member.setRoles(Arrays.asList(role)); 
			
			repo.save(member);
		}
	}
	
	
	public void testRead() {
		
		Optional<Member> result = repo.findById("user85");
		
		result.ifPresent(member -> log.info("member" + member)); 
	}
	
	public void testUpdateOldMember() {
		
		List<String> ids = new ArrayList<>();
		
		for(int i=0; i <= 100; i++) {
			ids.add("user" + i);
		}
		
		repo.findAllById(ids).forEach(member -> { 
			member.setUpw(passwordEncoder.encode(member.getUpw()));
			repo.save(member);
		}); 
		
	}
}
