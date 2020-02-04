package com.cbcho.shop.service;

import java.util.List;

import com.cbcho.shop.domain.CodeDetail;

public interface CodeDetailService {
	
	void register(CodeDetail codeDetail) throws Exception;
	List<CodeDetail> list() throws Exception;
	CodeDetail read(CodeDetail codeDetail) throws Exception;
	// 수정
	void modify(CodeDetail codeDetail) throws Exception;
	// 삭제
	void remove(CodeDetail codeDetail) throws Exception;
}
