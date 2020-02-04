package com.cbcho.shop.domain;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CodeClass {
	
	private String classCode;
	private String className;
	private String useYn;
	private Date regDate;
	private Date updDate;
}
