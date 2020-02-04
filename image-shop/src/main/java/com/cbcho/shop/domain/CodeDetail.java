package com.cbcho.shop.domain;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CodeDetail {
	
	private String classCode;
	private String codeValue;
	private String codeName;
	private int sortSeq;
	private String useYn;
	private Date regDate;
	private Date updDate;
	
}
