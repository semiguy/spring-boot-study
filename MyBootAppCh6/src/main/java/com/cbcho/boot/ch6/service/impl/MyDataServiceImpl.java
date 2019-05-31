package com.cbcho.boot.ch6.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbcho.boot.ch6.model.MyData;
import com.cbcho.boot.ch6.repository.MyDataRepository;
import com.cbcho.boot.ch6.service.MyDataService;

@Service
public class MyDataServiceImpl implements MyDataService {
	
	@Autowired
	private MyDataRepository myDataRepository;

	@Override
	public List<MyData> findAll() {
		
		return myDataRepository.findAll();
	}

	@Override
	public void save(MyData data) {
		
		myDataRepository.save(data);
		
	}

	@Override
	public MyData findById(Long id) {
		
		Optional<MyData> optional = myDataRepository.findById(id);
		
		if(optional.isPresent()) {
			MyData data = optional.get();
			
			return data;
		} else {
			throw new NullPointerException();
		}
	}

	@Override
	public void deleteById(Long id) {
		
		myDataRepository.deleteById(id); 
		
	}

	@Override
	public List<MyData> findAllOrderByName() {
		
		return myDataRepository.findAllOrderByName();
	}

	@Override
	public List<MyData> findByAge(int min, int max) {
		
		return myDataRepository.findByAge(min, max);
	}
	
	
}
