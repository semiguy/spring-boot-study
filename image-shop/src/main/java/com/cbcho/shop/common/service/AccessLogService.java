package com.cbcho.shop.common.service;

import java.util.List;

import com.cbcho.shop.common.domain.AccessLog;

public interface AccessLogService {
	
	public void register(AccessLog accessLog) throws Exception;
	public List<AccessLog> list() throws Exception;
}
