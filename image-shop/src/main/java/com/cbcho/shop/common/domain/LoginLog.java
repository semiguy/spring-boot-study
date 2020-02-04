package com.cbcho.shop.common.domain;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginLog {
	
	private int userNo;
	private String userId;
	private String remoteAddr;
	private Date regDate;
}
