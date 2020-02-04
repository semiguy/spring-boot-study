package com.cbcho.shop.mapper;

import java.util.List;

import com.cbcho.shop.domain.Notice;

public interface NoticeMapper {
	
	void create(Notice notice) throws Exception;
	List<Notice> list() throws Exception;
	Notice read(Integer noticeNo) throws Exception;
	// 수정
	void update(Notice notice) throws Exception;
	// 삭제
	void delete(Integer noticeNo) throws Exception;
}
