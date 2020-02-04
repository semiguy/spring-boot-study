package com.cbcho.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbcho.shop.domain.CodeDetail;
import com.cbcho.shop.mapper.CodeDetailMapper;
import com.cbcho.shop.service.CodeDetailService;

@Service
public class CodeDetailServiceImpl implements CodeDetailService {
	
	private final CodeDetailMapper codeDetailMapper;
	
	@Autowired
	public CodeDetailServiceImpl(CodeDetailMapper codeDetailMapper) {
		
		this.codeDetailMapper = codeDetailMapper;
	}
	
	@Override
	public void register(CodeDetail codeDetail) throws Exception {
		
		String classCode = codeDetail.getClassCode();
		int maxSortSeq = 0;
		try {
			maxSortSeq = codeDetailMapper.getMaxSortSeq(classCode);
		} catch (NullPointerException e) {
			maxSortSeq = 0;
		}	
		
		codeDetail.setSortSeq(maxSortSeq + 1);
		
		codeDetailMapper.create(codeDetail);
	}

	@Override
	public List<CodeDetail> list() throws Exception {
		
		return codeDetailMapper.list();
	}

	@Override
	public CodeDetail read(CodeDetail codeDetail) throws Exception {
		
		return codeDetailMapper.read(codeDetail);
	}

	@Override
	public void modify(CodeDetail codeDetail) throws Exception {
		
		codeDetailMapper.update(codeDetail);
		
	}

	@Override
	public void remove(CodeDetail codeDetail) throws Exception {
		
		codeDetailMapper.delete(codeDetail);
	}
	
	
	
}
