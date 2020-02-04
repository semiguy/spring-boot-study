package com.cbcho.shop.service;

import java.util.List;

import com.cbcho.shop.domain.CodeClass;

public interface CodeClassService {
	
	void register(CodeClass codeClass) throws Exception;
	List<CodeClass> list() throws Exception;
	CodeClass read(String classCode) throws Exception;
	//수정
	void modify(CodeClass codeClass) throws Exception;
	//삭제
	void remove(String classCode) throws Exception;
}
