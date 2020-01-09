package com.cbcho.exception.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cbcho.exception.domain.Board;
import com.cbcho.exception.domain.SearchQuery;
import com.cbcho.exception.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/boards")
public class BoardController {
	
	private final BoardService boardService;
	
	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@RequestMapping(value = "/{boardNo}", method = RequestMethod.GET)
	public ResponseEntity<Board> read(@PathVariable("boardNo") Long boardNo) throws Exception {
		
		log.info("read()...");
		
		// 글이 존재하지 않으면 사용자가 정의한 예외를 발생
		/*
		Board board = boardService.read(boardNo); 
		return new ResponseEntity<Board>(board, HttpStatus.OK);
		*/
		ResponseEntity<Board> entity = null;
		// try-catch 문으로 예외 처리
		try {
			Board board = boardService.read(boardNo);
			entity = new ResponseEntity<Board>(board, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Board>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Board>> list() throws Exception {
		
		log.info("list()...");
		
		//return new ResponseEntity<List<Board>>(boardService.list(), HttpStatus.OK);
		
		ResponseEntity<List<Board>> entity = null;
		try {
			entity = new ResponseEntity<List<Board>>(boardService.list(), HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<List<Board>>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
		
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> register(@Validated @RequestBody Board board, UriComponentsBuilder uriBuilder) throws Exception {
		
		log.info("register()...");
		/*
		boardService.register(board);
		
		log.info("board.getBoardNo() = " + board.getBoardNo());
		
		URI resourceUri = uriBuilder.path("boards/{boardNo}")
				.buildAndExpand(board.getBoardNo())
				.encode()
				.toUri();
		
		return ResponseEntity.created(resourceUri).build();
		*/
		ResponseEntity<String> entity = null;
		
		try {
			boardService.register(board);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value = "/{boardNo}", method = RequestMethod.PUT)
	public ResponseEntity<String> modify(@PathVariable("boardNo") Long boardNo, @Validated @RequestBody Board board) throws Exception {
		
		log.info("modify()...");
		
		/*
		board.setBoardNo(boardNo);
		boardService.modify(board);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		*/
		ResponseEntity<String> entity = null;
		try {
			board.setBoardNo(boardNo);
			boardService.modify(board);
			
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value = "/{boardNo}", method = RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable("boardNo") Long boardNo) throws Exception {
		
		log.info("remove()...");
		/*
		boardService.remove(boardNo);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		*/
		
		ResponseEntity<String> entity = null;
		try {
			boardService.remove(boardNo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseEntity<List<Board>> search(@Validated @RequestBody SearchQuery searchQuery, Model model) throws Exception {
		
		log.info("search");
		
		String title = searchQuery.getTitle();
		
		log.info("search title = " + title);
		
		return new ResponseEntity<List<Board>>(boardService.search(title), HttpStatus.OK);
	}
}
