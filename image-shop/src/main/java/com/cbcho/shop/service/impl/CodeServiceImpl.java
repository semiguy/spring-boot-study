package com.cbcho.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbcho.shop.common.domain.CodeLabelValue;
import com.cbcho.shop.mapper.CodeMapper;
import com.cbcho.shop.service.CodeService;

@Service
public class CodeServiceImpl implements CodeService {

	
	private final CodeMapper codeMapper;
	
	@Autowired
	public CodeServiceImpl(CodeMapper codeMapper) {
		
		this.codeMapper = codeMapper;
	}
	
	@Override
	public List<CodeLabelValue> getCodeClassList() throws Exception {
		
		return codeMapper.getCodeClassList();
	}

	@Override
	public List<CodeLabelValue> getCodeList(String classCode) throws Exception {
		
		return codeMapper.getCodeList(classCode);
	}
	
	
	
}
