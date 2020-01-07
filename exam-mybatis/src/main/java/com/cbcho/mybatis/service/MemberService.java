package com.cbcho.mybatis.service;

import java.util.List;

import com.cbcho.mybatis.domain.Member;

public interface MemberService {
	
	void register(Member member) throws Exception;
	
	List<Member> list() throws Exception;
	
	Member read(int userNo) throws Exception;
	
	void modify(Member member) throws Exception;
	
	void remove(int userNo) throws Exception;
}
