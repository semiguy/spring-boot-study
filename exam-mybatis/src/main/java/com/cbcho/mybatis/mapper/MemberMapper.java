package com.cbcho.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cbcho.mybatis.domain.Member;
import com.cbcho.mybatis.domain.MemberAuth;

@Mapper
public interface MemberMapper {
	
	void create(Member member) throws Exception;
	
	Member read(int userNo) throws Exception;
	
	void update(Member member) throws Exception;
	
	void delete(int userNo) throws Exception;
	
	List<Member> list() throws Exception;
	
	void createAuth(MemberAuth memberAuth) throws Exception;
	
	void deleteAuth(int userNo) throws Exception;
}
