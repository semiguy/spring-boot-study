package com.cbcho.aop.service;

import java.util.List;

import com.cbcho.aop.domain.Board;


public interface BoardService {
	
	void register(Board board) throws Exception;
	
	Board read(Long boardNo) throws Exception;
	
	void modify(Board board) throws Exception;
	
	void remove(Long boardNo) throws Exception;
	
	public List<Board> list() throws Exception;
	
	List<Board> search(String title) throws Exception;
}
