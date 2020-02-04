package com.cbcho.shop.common.mapper;

import java.util.List;

import com.cbcho.shop.common.domain.AccessLog;

public interface AccessLogMapper {
	
	public void create(AccessLog accessLog) throws Exception;
	public List<AccessLog> list() throws Exception;
}
