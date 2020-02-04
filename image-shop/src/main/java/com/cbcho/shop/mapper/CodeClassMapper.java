package com.cbcho.shop.mapper;

import java.util.List;

import com.cbcho.shop.domain.CodeClass;

public interface CodeClassMapper {
	
	void create(CodeClass codeClass) throws Exception;
	List<CodeClass> list() throws Exception;
	CodeClass read(String classCode) throws Exception;
	//수정
	void update(CodeClass codeClass) throws Exception;
	//삭제
	void delete(String classCode) throws Exception;
}
