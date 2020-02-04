package com.cbcho.shop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbcho.shop.domain.Pds;
import com.cbcho.shop.mapper.PdsMapper;
import com.cbcho.shop.service.PdsService;

@Service
public class PdsServiceImpl implements PdsService {
	
	private final PdsMapper pdsMapper;
	
	public PdsServiceImpl(PdsMapper pdsMapper) {
		
		this.pdsMapper = pdsMapper;
	}
	
	@Transactional
	@Override
	public void register(Pds item) throws Exception {
		
		pdsMapper.create(item);
		
		String[] files = item.getFiles();
		
		if(files == null) {
			return;
		}
		
		for(String fileName : files) {
			pdsMapper.addAttach(fileName);
		}
	}

	@Override
	public List<Pds> list() throws Exception {
		
		return pdsMapper.list();
	}

	@Override
	public Pds read(Integer itemId) throws Exception {
		
		pdsMapper.updateViewCnt(itemId);
		
		return pdsMapper.read(itemId);
	}
	
	@Transactional
	@Override
	public void modify(Pds item) throws Exception {
		
		pdsMapper.update(item);
		
		Integer itemId = item.getItemId();
		
		pdsMapper.deleteAttach(itemId);
		
		String[] files = item.getFiles();
		
		if(files == null) {
			return;
		}
		
		for(String fileName : files) {
			pdsMapper.replaceAttach(fileName, itemId);
		}
	}
	
	@Transactional
	@Override
	public void remove(Integer itemId) throws Exception {
		
		pdsMapper.deleteAttach(itemId);
		pdsMapper.delete(itemId); 
		
	}

	@Override
	public List<String> getAttach(Integer itemId) throws Exception {
		
		return pdsMapper.getAttach(itemId);
	}

	@Override
	public void updateAttachDownCnt(String fullName) throws Exception {
		
		pdsMapper.updateAttachDownCnt(fullName);
	}
	
	
	
}
