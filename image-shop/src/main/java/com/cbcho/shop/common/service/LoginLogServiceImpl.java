package com.cbcho.shop.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbcho.shop.common.domain.LoginLog;
import com.cbcho.shop.common.mapper.LoginLogMapper;

@Service
public class LoginLogServiceImpl implements LoginLogService {
	
	private final LoginLogMapper loginLogMapper;
	
	@Autowired
	public LoginLogServiceImpl(LoginLogMapper loginLogMapper) {
		
		this.loginLogMapper = loginLogMapper;
	}

	@Override
	public void register(LoginLog loginLog) throws Exception {
		
		loginLogMapper.create(loginLog);
	}

	@Override
	public List<LoginLog> list() throws Exception {
		
		return loginLogMapper.list();
	}
	
	
}
