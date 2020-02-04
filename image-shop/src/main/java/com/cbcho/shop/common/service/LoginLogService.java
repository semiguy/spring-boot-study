package com.cbcho.shop.common.service;

import java.util.List;

import com.cbcho.shop.common.domain.LoginLog;

public interface LoginLogService {
	
	public void register(LoginLog loginLog) throws Exception;
	public List<LoginLog> list() throws Exception;
}
