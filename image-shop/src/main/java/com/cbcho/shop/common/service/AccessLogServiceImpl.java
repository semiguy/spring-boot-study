package com.cbcho.shop.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbcho.shop.common.domain.AccessLog;
import com.cbcho.shop.common.mapper.AccessLogMapper;

@Service
public class AccessLogServiceImpl implements AccessLogService {
	
	@Autowired
	private AccessLogMapper accessLogMapper;

	@Override
	public void register(AccessLog accessLog) throws Exception {
		
		accessLogMapper.create(accessLog);
	}

	@Override
	public List<AccessLog> list() throws Exception {
		
		return accessLogMapper.list();
	}
	
	
}
