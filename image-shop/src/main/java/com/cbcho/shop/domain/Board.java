package com.cbcho.shop.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {
	
	private int boardNo;
	private String title;
	private String content;
	private String writer;
	private Date regDate;
	
	// 페이징요청 정보를 멤버변수로 선언
	//private PageRequest pageRequest;
	
	// 페이징요청 멤버변수를 사용하여 페이징정보를 반환
	/*
	public int getPage() {
		return pageRequest.getPage();
	}
	
	public int getSizePerPage() {
		return pageRequest.getSizePerPage();
	}
	*/
	
	// 페이징 요청 멤버변수의 Getter/Setter 메서드를 정의한다.
	/*
	public PageRequest getPageRequest() {
		return pageRequest;
	}
	
	public void setPageRequest(PageRequest pageRequest) {
		this.pageRequest = pageRequest;
	}
	*/
}
