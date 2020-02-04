package com.cbcho.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbcho.shop.domain.Member;
import com.cbcho.shop.domain.MemberAuth;
import com.cbcho.shop.mapper.MemberMapper;
import com.cbcho.shop.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {
	
	private final MemberMapper memberMapper;
	
	@Autowired
	public MemberServiceImpl(MemberMapper memberMapper) {
		
		this.memberMapper = memberMapper;
	}
	
	@Transactional
	@Override
	public void register(Member member) throws Exception {
		
		memberMapper.create(member);
		
		MemberAuth memberAuth = new MemberAuth();
		
		memberAuth.setUserNo(member.getUserNo());
		memberAuth.setAuth("ROLE_MEMBER");
		
		memberMapper.createAuth(memberAuth);
		
	}

	@Override
	public Member read(int userNo) throws Exception {
		
		return memberMapper.read(userNo);
	}
	
	@Transactional
	@Override
	public void modify(Member member) throws Exception {
		
		memberMapper.update(member);
		
		int userNo = member.getUserNo();
		
		memberMapper.deleteAuth(userNo);
		
		List<MemberAuth> authList = member.getAuthList();
		
		for(int i=0; i < authList.size(); i++) {
			
			MemberAuth memberAuth = authList.get(i);
			String auth = memberAuth.getAuth();
			
			if(auth == null) {
				continue;
			}
			
			if(auth.trim().length() == 0) {
				continue;
			}
			
			memberAuth.setUserNo(userNo); 
			memberMapper.createAuth(memberAuth); 
		}
		
	}
	
	@Transactional
	@Override
	public void remove(int userNo) throws Exception {
		
		memberMapper.deleteAuth(userNo);
		memberMapper.delete(userNo); 
		
	}
	
	@Override
	public List<Member> list() throws Exception {
		
		return memberMapper.list();
	}

	@Override
	public int getCoin(int userNo) throws Exception {
		
		Member member = memberMapper.read(userNo);
		
		return member.getCoin();
	}
	
	// 회원 테이블의 건수를 반환
	@Override
	public int countAll() throws Exception {
		
		return memberMapper.countAll();
	}
	
	// 최초 관리자 생성을 위한 데이터를 등록
	@Transactional
	@Override
	public void setupAdmin(Member member) throws Exception {
		
		memberMapper.create(member);
		
		MemberAuth memberAuth = new MemberAuth();
		memberAuth.setUserNo(member.getUserNo());
		memberAuth.setAuth("ROLE_ADMIN");
		
		memberMapper.createAuth(memberAuth); 
	}
	
	
	
}
