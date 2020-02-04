package com.cbcho.shop.service;

import java.util.List;

import com.cbcho.shop.common.domain.PageRequest;
import com.cbcho.shop.domain.Board;

public interface BoardService {
	
	void register(Board board) throws Exception;
	// 목록
	List<Board> list() throws Exception;
	// 페이지요청 정보를 매개변수로 받아 페이징 처리를 한 게시글 목록을 반환
	List<Board> list(PageRequest pageRequest) throws Exception;
	// 게시글 전체 전수를 반환
	int count() throws Exception;
	// 검색처리된 게시글 건수를 반환
	int count(PageRequest pageRequest) throws Exception;
	
	// 상세화면
	Board read(Integer boardNo) throws Exception;
	// 수정
	void modify(Board board) throws Exception;
	// 삭제
	void remove(Integer boardNo) throws Exception;
}
