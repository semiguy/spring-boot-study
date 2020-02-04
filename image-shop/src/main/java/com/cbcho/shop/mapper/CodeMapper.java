package com.cbcho.shop.mapper;

import java.util.List;

import com.cbcho.shop.common.domain.CodeLabelValue;

public interface CodeMapper {
	
	List<CodeLabelValue> getCodeClassList() throws Exception;
	List<CodeLabelValue> getCodeList(String classCode) throws Exception;
}
