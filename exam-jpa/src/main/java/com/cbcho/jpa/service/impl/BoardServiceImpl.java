package com.cbcho.jpa.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbcho.jpa.domain.Board;
import com.cbcho.jpa.repository.BoardRepository;
import com.cbcho.jpa.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {
	
	private final BoardRepository boardRepository;
	
	@Autowired
	public BoardServiceImpl(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}

	@Override
	@Transactional
	public void register(Board board) throws Exception {
		
		boardRepository.save(board);		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Board> list() throws Exception {
		
		return boardRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Board read(Integer boardNo) throws Exception {
		
		/*
		Board board = boardRepository.getOne(boardNo);
		
		return board;
		*/
		
		Optional<Board> opt = boardRepository.findById(boardNo); 
		
		if(opt.isPresent()) {
			Board board = opt.get();
			
			return board;
		} else {
			throw new NullPointerException();
		}
		
	}

	@Override
	@Transactional
	public void modify(Board board) throws Exception {
		
		Board boardEntity = boardRepository.getOne(board.getBoardNo());
		
		boardEntity.setTitle(board.getTitle());
		boardEntity.setContent(board.getContent()); 
		
		boardRepository.save(boardEntity);
	}

	@Override
	@Transactional
	public void remove(Integer boardNo) throws Exception {
		
		boardRepository.deleteById(boardNo); 
		
	}
	
	
	
}
