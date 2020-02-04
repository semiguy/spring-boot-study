package com.cbcho.shop.mapper;

import java.util.List;

import com.cbcho.shop.common.domain.PageRequest;
import com.cbcho.shop.domain.Board;

public interface BoardMapper {
	
	void create(Board board) throws Exception;
	List<Board> list() throws Exception;
	// 상세 보기
	Board read(Integer boardNo) throws Exception;
	// 수정
	void update(Board board) throws Exception;
	// 삭제
	void delete(Integer boardNo) throws Exception;
	
	// 페이징요청 정보를 매개변수로 받아 페이징 처리를 한 게시글 목록을 반환한다.
	List<Board> list(PageRequest pageRequest) throws Exception;
	
	// 게시글 전체 건수를 반환한다.
	int count() throws Exception;
	// 검색처리된 게시글 건수를 반환
	int count(PageRequest pageRequest) throws Exception;
}
