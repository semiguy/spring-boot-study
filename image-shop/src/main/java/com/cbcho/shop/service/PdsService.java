package com.cbcho.shop.service;

import java.util.List;

import com.cbcho.shop.domain.Pds;

public interface PdsService {
	
	public void register(Pds item) throws Exception;
	public List<Pds> list() throws Exception;
	public Pds read(Integer itemId) throws Exception;
	public void modify(Pds item) throws Exception;
	public void remove(Integer itemId) throws Exception;
	public List<String> getAttach(Integer itemId) throws Exception;
	public void updateAttachDownCnt(String fullName) throws Exception;
}
