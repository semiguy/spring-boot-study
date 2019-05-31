package com.cbcho.boot.ch6.dao;

import java.io.Serializable;
import java.util.List;

import com.cbcho.boot.ch6.model.MyData;

public interface MyDataDao <T> extends Serializable {
	
	// 모든 엔터티를 가져오는 역할
	public List<T> getAll();
	
	// ID 번호를 인수로 지정해서 엔터티를 검색.
	public T findById(long id);
	
	// 이름으로 엔터티를 검색.
	public List<T> findByName(String name);
	
	public List<T> find(String fstr);
	
	public List<MyData> findByAge(int min, int max);
}
