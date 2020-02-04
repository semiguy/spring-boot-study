package com.cbcho.shop.mapper;

import java.util.List;

import com.cbcho.shop.domain.CodeDetail;

public interface CodeDetailMapper {
	
	void create(CodeDetail codeDetail) throws Exception;
	Integer getMaxSortSeq(String classCode) throws Exception;
	List<CodeDetail> list() throws Exception;
	CodeDetail read(CodeDetail codeDetail) throws Exception;
	// 수정
	void update(CodeDetail codeDetail) throws Exception;
	// 삭제
	void delete(CodeDetail codeDetail) throws Exception;
}

