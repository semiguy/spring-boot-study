package com.cbcho.jpa.service;

import java.util.List;

import com.cbcho.jpa.domain.Board;

public interface BoardService {
	
	void register(Board board) throws Exception;
	
	List<Board> list() throws Exception;
	
	Board read(Integer boardNo) throws Exception;
	
	void modify(Board board) throws Exception;
	
	void remove(Integer boardNo) throws Exception;
}
