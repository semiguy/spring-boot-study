package com.cbcho.boot.ch7.component;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.cbcho.boot.ch7.model.MyData;
import com.cbcho.boot.ch7.repository.MyDataRepository;

public class MyDataBean {
	
	@Autowired
	MyDataRepository repository;
	
	public String getTableTagById(Long id) {
		
		Optional<MyData> optional = repository.findById(id);
		
		if(optional.isPresent()) {
			MyData data = optional.get();
			
			String result = "<tr><td>" + data.getName() + "</td></tr>"
					+ "<tr><td>" + data.getMail() + "</td></tr>" 
					+ "<tr><td>" + data.getAge() + "</td></tr>" 
					+ "<tr><td>" + data.getMemo() + "</td></tr>";
			
			return result;
		} else {
			throw new NullPointerException();
		}
	}
}
