package com.cbcho.security.domain;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Member {
	
	private int userNo;
	private String userId;
	private String userPw;
	private String userName;
	private boolean enabled;
	
	private Date regDate;
	private Date updDate;
	
	private List<MemberAuth> authList;
	
}
