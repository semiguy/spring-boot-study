package com.cbcho.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbcho.shop.domain.CodeClass;
import com.cbcho.shop.mapper.CodeClassMapper;
import com.cbcho.shop.service.CodeClassService;

@Service
public class CodeClassServiceImpl implements CodeClassService {
	
	private final CodeClassMapper mapper;
	
	@Autowired
	public CodeClassServiceImpl(CodeClassMapper mapper) {
		
		this.mapper = mapper;
	}
	
	@Override
	public void register(CodeClass codeClass) throws Exception {
		
		mapper.create(codeClass);
	}

	@Override
	public List<CodeClass> list() throws Exception {
		
		return mapper.list();
	}

	@Override
	public CodeClass read(String classCode) throws Exception {
		
		return mapper.read(classCode); 
	}

	@Override
	public void modify(CodeClass codeClass) throws Exception {
		
		mapper.update(codeClass);
	}

	@Override
	public void remove(String classCode) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
