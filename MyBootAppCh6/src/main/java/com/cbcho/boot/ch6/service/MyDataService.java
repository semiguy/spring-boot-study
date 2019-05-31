package com.cbcho.boot.ch6.service;

import java.util.List;

import com.cbcho.boot.ch6.model.MyData;

public interface MyDataService {
	
	public List<MyData> findAll();
	public void save(MyData data);
	public MyData findById(Long id);
	public void deleteById(Long id);
	public List<MyData> findAllOrderByName();
	public List<MyData> findByAge(int min, int max);
}
