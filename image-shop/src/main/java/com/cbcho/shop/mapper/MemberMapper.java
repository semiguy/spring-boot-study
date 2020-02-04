package com.cbcho.shop.mapper;

import java.util.List;

import com.cbcho.shop.domain.Member;
import com.cbcho.shop.domain.MemberAuth;

public interface MemberMapper {
	
	Member readByUserId(String userId);
	// 생성
	void create(Member member) throws Exception;
	
	// 상세보기
	Member read(int userNo) throws Exception;
	// 수정
	void update(Member member) throws Exception;
	// 삭제
	void delete(int userNo) throws Exception;
	// 리스트
	List<Member> list() throws Exception;
	
	// 권한 설정
	void createAuth(MemberAuth memberAuth) throws Exception;
	// 권한 삭제
	void deleteAuth(int userNo) throws Exception;
	
	int countAll() throws Exception;
}
