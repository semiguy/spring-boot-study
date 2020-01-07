package com.cbcho.jdbc.service;

import java.util.List;

import com.cbcho.jdbc.domain.Board;

public interface BoardService {
	
	void register(Board board) throws Exception;
	
	Board read(Long boardNo) throws Exception;
	
	void modify(Board board) throws Exception;
	
	void remove(Long boardNo) throws Exception;
	
	List<Board> list() throws Exception;
}
