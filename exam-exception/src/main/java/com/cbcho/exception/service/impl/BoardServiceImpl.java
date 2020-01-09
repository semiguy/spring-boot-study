package com.cbcho.exception.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbcho.exception.common.exception.BoardRecordNotFoundException;
import com.cbcho.exception.domain.Board;
import com.cbcho.exception.mapper.BoardMapper;
import com.cbcho.exception.service.BoardService;

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
		
		Board board = mapper.read(boardNo.intValue());
		
		// 게시판의 글이 존재하지 않으면 사용자가 정의한 예외를 발생시킨다.
		if(board == null) {
			throw new BoardRecordNotFoundException("Not Found boardNo=" + boardNo);
		}
		
		return board; 
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
