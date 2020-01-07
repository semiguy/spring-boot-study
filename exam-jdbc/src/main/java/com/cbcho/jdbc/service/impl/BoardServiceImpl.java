package com.cbcho.jdbc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbcho.jdbc.dao.BoardDAO;
import com.cbcho.jdbc.domain.Board;
import com.cbcho.jdbc.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {
	
	private final BoardDAO dao;
	
	@Autowired
	public BoardServiceImpl(BoardDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public void register(Board board) throws Exception {
		
		dao.create(board);
	}

	@Override
	public Board read(Long boardNo) throws Exception {
		
		return dao.read(boardNo);
	}

	@Override
	public void modify(Board board) throws Exception {
		
		dao.update(board);
	}

	@Override
	public void remove(Long boardNo) throws Exception {
		
		dao.delete(boardNo); 
	}

	@Override
	public List<Board> list() throws Exception {
		
		return dao.list();
	}
	
}
