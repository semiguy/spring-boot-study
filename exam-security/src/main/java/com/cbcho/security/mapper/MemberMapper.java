package com.cbcho.security.mapper;

import com.cbcho.security.domain.Member;

public interface MemberMapper {
	
	Member read(String userId);
}
