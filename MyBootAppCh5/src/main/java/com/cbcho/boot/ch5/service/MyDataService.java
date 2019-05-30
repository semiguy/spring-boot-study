package com.cbcho.boot.ch5.service;

import java.util.List;

import com.cbcho.boot.ch5.model.MyData;

public interface MyDataService {
	
	List<MyData> findAll();
	void save(MyData data);
	MyData findById(Long id);
	void deleteById(Long id);
}
