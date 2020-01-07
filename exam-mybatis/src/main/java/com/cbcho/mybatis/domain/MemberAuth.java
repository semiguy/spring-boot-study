package com.cbcho.mybatis.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberAuth {
	
	private int userNo;
	private String auth;
}
