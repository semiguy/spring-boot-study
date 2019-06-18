package com.cbcho.boot02.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cbcho.boot02.model.WebBoard;
import com.cbcho.boot02.repository.WebBoardRepository;

@Service
public class WebBoardService {
	
	@Autowired
	WebBoardRepository webBoardRepo;
	
	public Page<WebBoard> getWebBoardListAll(Pageable pageable) {
		
		pageable = PageRequest.of(
				pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, 
				pageable.getPageSize());
		
		return webBoardRepo.findAll(pageable);
	}
}
