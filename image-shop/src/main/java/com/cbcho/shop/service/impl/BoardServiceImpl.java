package com.cbcho.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbcho.shop.common.domain.PageRequest;
import com.cbcho.shop.domain.Board;
import com.cbcho.shop.mapper.BoardMapper;
import com.cbcho.shop.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {
	
	private final BoardMapper boardMapper;
	
	@Autowired
	public BoardServiceImpl(BoardMapper boardMapper) {
		
		this.boardMapper = boardMapper;
	}

	@Override
	public void register(Board board) throws Exception {
		
		boardMapper.create(board);
		
	}

	@Override
	public List<Board> list() throws Exception {
		
		return boardMapper.list();
	}

	@Override
	public Board read(Integer boardNo) throws Exception {
		
		return boardMapper.read(boardNo);
	}

	@Override
	public void modify(Board board) throws Exception {
		
		boardMapper.update(board);
	}

	@Override
	public void remove(Integer boardNo) throws Exception {
		
		boardMapper.delete(boardNo);
	}

	@Override
	public List<Board> list(PageRequest pageRequest) throws Exception {
		
		return boardMapper.list(pageRequest);
	}

	@Override
	public int count() throws Exception {
		
		return boardMapper.count();
	}

	@Override
	public int count(PageRequest pageRequest) throws Exception {
		
		return boardMapper.count(pageRequest);
	}
	
	
	
}
