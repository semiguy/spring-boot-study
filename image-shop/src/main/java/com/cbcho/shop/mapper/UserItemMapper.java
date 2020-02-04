package com.cbcho.shop.mapper;

import java.util.List;

import com.cbcho.shop.domain.UserItem;

public interface UserItemMapper {
	
	public void create(UserItem userItem) throws Exception;
	// 조회
	public List<UserItem> list(Integer userNo) throws Exception;
	public List<UserItem> listAll() throws Exception;
	// 상세보기
	public UserItem read(Integer userItemNo) throws Exception;
}
