package com.cbcho.shop.common.domain;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccessLog {
	
	private String requestUri;
	private String className;
	private String classSimpleName;
	private String methodName;
	private String remoteAddr;
	private Date regDate;
}
