package com.cbcho.aop.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Board {
	
	private long boardNo;
	private String title;
	private String content;
	private String writer;
	private Date regDate;
}
