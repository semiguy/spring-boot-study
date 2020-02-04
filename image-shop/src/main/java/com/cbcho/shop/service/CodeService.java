package com.cbcho.shop.service;

import java.util.List;

import com.cbcho.shop.common.domain.CodeLabelValue;

public interface CodeService {
	
	List<CodeLabelValue> getCodeClassList() throws Exception;
	List<CodeLabelValue> getCodeList(String classCode) throws Exception;
}
