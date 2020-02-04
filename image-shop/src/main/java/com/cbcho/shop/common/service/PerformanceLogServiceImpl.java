package com.cbcho.shop.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbcho.shop.common.domain.PerformanceLog;
import com.cbcho.shop.common.mapper.PerformanceLogMapper;

@Service
public class PerformanceLogServiceImpl implements PerformanceLogService {
	
	@Autowired
	private PerformanceLogMapper pLogMapper;
	
	@Override
	public void register(PerformanceLog performanceLog) throws Exception {
		
		pLogMapper.create(performanceLog);
	}

	@Override
	public List<PerformanceLog> list() throws Exception {
		
		return pLogMapper.list();
	}
	
	
}
