package com.cbcho.shop.common.mapper;

import java.util.List;

import com.cbcho.shop.common.domain.PerformanceLog;

public interface PerformanceLogMapper {
	
	public void create(PerformanceLog performanceLog) throws Exception;
	public List<PerformanceLog> list() throws Exception;
}
