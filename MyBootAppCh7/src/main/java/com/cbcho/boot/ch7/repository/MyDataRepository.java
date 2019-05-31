package com.cbcho.boot.ch7.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cbcho.boot.ch7.model.MyData;

public interface MyDataRepository extends JpaRepository<MyData, Long> {

}
