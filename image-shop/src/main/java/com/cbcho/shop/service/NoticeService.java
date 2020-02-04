package com.cbcho.shop.service;

import java.util.List;

import com.cbcho.shop.domain.Notice;

public interface NoticeService {
	
	void register(Notice notice) throws Exception;
	List<Notice> list() throws Exception;
	// 상세보기
	Notice read(Integer noticeNo) throws Exception;
	// 수정
	void modify(Notice notice) throws Exception;
	// 삭제
	void remove(Integer noticeNo) throws Exception;
}
