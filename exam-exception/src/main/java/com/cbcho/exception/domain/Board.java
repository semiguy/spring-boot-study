package com.cbcho.exception.domain;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class Board {
	
	private long boardNo;
	
	@NotBlank
	private String title;
	private String content;
	private String writer;
	private Date regDate;
}
