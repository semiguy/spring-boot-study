package com.cbcho.jpa.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cbcho.jpa.domain.Board;
import com.cbcho.jpa.service.BoardService;

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
		
		Board board = boardService.read(boardNo.intValue()); 
		
		return new ResponseEntity<Board>(board, HttpStatus.OK);
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Board>> list() throws Exception {
		
		log.info("list()...");
		
		return new ResponseEntity<List<Board>>(boardService.list(), HttpStatus.OK);
	}
		
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> register(@Validated @RequestBody Board board, UriComponentsBuilder uriBuilder) throws Exception {
		
		log.info("register()...");
		boardService.register(board);
		
		log.info("board.getBoardNo() = " + board.getBoardNo());
		
		URI resourceUri = uriBuilder.path("boards/{boardNo}")
				.buildAndExpand(board.getBoardNo())
				.encode()
				.toUri();
		
		return ResponseEntity.created(resourceUri).build();
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> modify(@Validated @RequestBody Board board) throws Exception {
		
		log.info("modify()...");
		
		board.setBoardNo(board.getBoardNo());
		boardService.modify(board);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);		
	}
	
	@RequestMapping(value = "/{boardNo}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> remove(@PathVariable("boardNo") Long boardNo) throws Exception {
		
		log.info("remove()...");
		boardService.remove(boardNo.intValue());
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}		
}
