package com.cbcho.boot.ch5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cbcho.boot.ch5.model.MyData;

@Repository
public interface MyDataRepository extends JpaRepository<MyData, Long> {
	
	
}
