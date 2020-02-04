package com.cbcho.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbcho.shop.domain.Notice;
import com.cbcho.shop.mapper.NoticeMapper;
import com.cbcho.shop.service.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	private final NoticeMapper noticeMapper;
	
	@Autowired
	public NoticeServiceImpl(NoticeMapper noticeMapper) {
		
		this.noticeMapper = noticeMapper;
	}

	@Override
	public void register(Notice notice) throws Exception {
		
		noticeMapper.create(notice);
	}

	@Override
	public List<Notice> list() throws Exception {
		
		return noticeMapper.list();
	}

	@Override
	public Notice read(Integer noticeNo) throws Exception {
		
		return noticeMapper.read(noticeNo);
	}

	@Override
	public void modify(Notice notice) throws Exception {
		
		noticeMapper.update(notice);
	}

	@Override
	public void remove(Integer noticeNo) throws Exception {
		
		noticeMapper.delete(noticeNo);
	}
	
	
	
}
