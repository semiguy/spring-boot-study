package com.cbcho.shop.service;

import java.util.List;

import com.cbcho.shop.domain.Item;

public interface ItemService {
	
	// 등록
	void register(Item item) throws Exception;
	// 조회
	List<Item> list() throws Exception;
	// 보기
	Item read(Integer itemId) throws Exception;
	// 수정
	void modify(Item item) throws Exception;
	// 삭제
	void remove(Integer itemId) throws Exception;
	// 미리보기
	String getPreview(Integer itemId) throws Exception;
	String getPicture(Integer itemId) throws Exception;
}
