package com.cbcho.boot.ch6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cbcho.boot.ch6.model.MsgData;

@Repository
public interface MsgDataRepository extends JpaRepository<MsgData, Long> {

	
}
