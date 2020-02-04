package com.cbcho.shop.service;

import java.util.List;

import com.cbcho.shop.domain.Item;
import com.cbcho.shop.domain.Member;
import com.cbcho.shop.domain.UserItem;

public interface UserItemService {
	
	// 구매등록
	public void register(Member member, Item item) throws Exception;
	// 조회
	public List<UserItem> list(Integer userNo) throws Exception;
	public List<UserItem> listAll() throws Exception;
	// 상세보기
	public UserItem read(Integer userItemNo) throws Exception;
}
