package com.cbcho.shop.service;

import java.util.List;

import com.cbcho.shop.domain.Member;

public interface MemberService {
	
	void register(Member member) throws Exception;
	Member read(int userNo) throws Exception;
	void modify(Member member) throws Exception;
	//삭제
	void remove(int userNo) throws Exception;
	// 조회
	List<Member> list() throws Exception;
	
	int getCoin(int userNo) throws Exception;
	// 회원 테이블의 데이터 건수를 반환
	int countAll() throws Exception;
	// 최초 관리자 생성을 위한 데이터를 등록
	void setupAdmin(Member member) throws Exception;
}
