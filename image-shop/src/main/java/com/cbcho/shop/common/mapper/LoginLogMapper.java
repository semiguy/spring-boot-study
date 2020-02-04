package com.cbcho.shop.common.mapper;

import java.util.List;

import com.cbcho.shop.common.domain.LoginLog;

public interface LoginLogMapper {
	
	public void create(LoginLog loginLog) throws Exception;
	public List<LoginLog> list() throws Exception;
}
