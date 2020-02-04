package com.cbcho.shop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cbcho.shop.common.domain.CodeLabelValue;
import com.cbcho.shop.common.domain.PageRequest;
import com.cbcho.shop.common.domain.Pagination;
import com.cbcho.shop.common.security.domain.CustomUser;
import com.cbcho.shop.domain.Board;
import com.cbcho.shop.domain.Member;
import com.cbcho.shop.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
	
	private final BoardService boardService;
	
	@Autowired
	public BoardController(BoardService boardService) {
		
		this.boardService = boardService;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public void registerForm(Model model, Authentication authentication) throws Exception {
		
		log.info("registerForm()...");
		
		CustomUser customUser = (CustomUser)authentication.getPrincipal();
		Member member = customUser.getMember();
		
		Board board = new Board();
		board.setWriter(member.getUserId()); 
		
		model.addAttribute(board);
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public String register(Board board, RedirectAttributes rttr) throws Exception {
		
		log.info("register()...");
		
		boardService.register(board);
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/board/list";
	}
	
	/**
	 * 리스트
	 * @param pageRequest
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	//public void list(Model model) throws Exception {
	public void list(@ModelAttribute("pgrq")PageRequest pageRequest, Model model) throws Exception {
		
		log.info("list()...");
		
		// 뷰에 페이징 처리를 한 게시글 목록을 전달다.
		//model.addAttribute("list", boardService.list());
		model.addAttribute("list", boardService.list(pageRequest));
		
		// 페이징 네비게이션 정보를 뷰에 전달한다.
		Pagination pagination = new Pagination();
		pagination.setPageRequest(pageRequest);
		// 페이지 네비게이션 정보에 검색처리된 게시글 건수를 저장
		pagination.setTotalCount(boardService.count(pageRequest));
		
		model.addAttribute("pagination", pagination);
		
		// 검색 유형의 코드명과 코드값을 정의한다.
		List<CodeLabelValue> searchTypeCodeValueList = new ArrayList<CodeLabelValue>();
		searchTypeCodeValueList.add(new CodeLabelValue("n", "--"));
		searchTypeCodeValueList.add(new CodeLabelValue("t", "Title"));
		searchTypeCodeValueList.add(new CodeLabelValue("c", "Content"));
		searchTypeCodeValueList.add(new CodeLabelValue("w", "Writer"));
		searchTypeCodeValueList.add(new CodeLabelValue("tc", "Title OR Content"));
		searchTypeCodeValueList.add(new CodeLabelValue("cw", "Content OR Writer"));
		searchTypeCodeValueList.add(new CodeLabelValue("tcw", "Title OR Content OR Writer"));
		
		model.addAttribute("searchTypeCodeValueList", searchTypeCodeValueList);
	}
	
	/**
	 * 상세화면
	 * @param boardNo
	 * @param pageRequest
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	//public void read(int boardNo, Model model) throws Exception {
	public void read(int boardNo, @ModelAttribute("pgrq") PageRequest pageRequest, Model model) throws Exception {
		
		log.info("read()...");
		//model.addAttribute(boardService.read(boardNo));
		
		// 조회한 게시글 상세정보를 뷰에 전달
		Board board = boardService.read(boardNo);
		
		//board.setPageRequest(pageRequest);
		
		model.addAttribute(board);
	}
	
	/**
	 * 수정
	 * @param boardNo
	 * @param pageRequest
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')")
	//public void modifyForm(int boardNo, Model model) throws Exception {
	public void modifyForm(int boardNo, @ModelAttribute("pgrq") PageRequest pageRequest, Model model) throws Exception {
		
		log.info("modifyForm()...");
		//model.addAttribute(boardService.read(boardNo));
		
		// 조회한 게시글 상세정보를 뷰에 전달
		Board board = boardService.read(boardNo);
		
		//board.setPageRequest(pageRequest);
		
		model.addAttribute(board);
	}
	
	/**
	 * 수정 처리
	 * @param board
	 * @param pageRequest
	 * @param rttr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')")
	//public String modify(Board board, RedirectAttributes rttr) throws Exception {
	public String modify(Board board, PageRequest pageRequest, RedirectAttributes rttr) throws Exception {
		
		log.info("modify()...");
		
		boardService.modify(board);
		
		// RedirectAttributes 객체에 일회성 데이터를 지정하여 전달
		rttr.addAttribute("page", pageRequest.getPage());
		rttr.addAttribute("sizePerPage", pageRequest.getSizePerPage());
		// 검색유형과 검색어를 뷰에 전달
		rttr.addAttribute("searchType", pageRequest.getSearchType());
		rttr.addAttribute("keyword", pageRequest.getKeyword());
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/board/list";
	}
	
	/**
	 * 삭제처리
	 * @param boardNo
	 * @param pageRequest
	 * @param rttr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')")
	//public String remove(int boardNo, RedirectAttributes rttr) throws Exception {
	public String remove(int boardNo, PageRequest pageRequest, RedirectAttributes rttr) throws Exception {
		
		log.info("remove()...");
		
		boardService.remove(boardNo);
		
		// RedirectAttributes 객체에 일회성 데이터를 지정하여 전달
		rttr.addAttribute("page", pageRequest.getPage());
		rttr.addAttribute("sizePerPage", pageRequest.getSizePerPage());
		// 검색유형과 검색어를 뷰에 전달
		rttr.addAttribute("searchType", pageRequest.getSearchType());
		rttr.addAttribute("keyword", pageRequest.getKeyword());
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/board/list";
	}
	
}
