package com.cbcho.mybatis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbcho.mybatis.domain.Board;
import com.cbcho.mybatis.mapper.BoardMapper;
import com.cbcho.mybatis.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {
	
	private final BoardMapper mapper;
	
	@Autowired
	public BoardServiceImpl(BoardMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public void register(Board board) throws Exception {
		mapper.create(board);
		
	}

	@Override
	public Board read(Long boardNo) throws Exception {
		
		return mapper.read(boardNo.intValue()); 
	}

	@Override
	public void modify(Board board) throws Exception {
		
		mapper.update(board);
	}

	@Override
	public void remove(Long boardNo) throws Exception {
		
		mapper.delete(boardNo.intValue()); 
	}

	@Override
	public List<Board> list() throws Exception {
		
		return mapper.list();
	}

	@Override
	public List<Board> search(String title) throws Exception {
		
		return mapper.search(title); 
	}
	
	
}
