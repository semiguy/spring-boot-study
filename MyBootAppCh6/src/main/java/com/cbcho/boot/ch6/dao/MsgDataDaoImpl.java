package com.cbcho.boot.ch6.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.cbcho.boot.ch6.model.MsgData;

public class MsgDataDaoImpl implements MsgDataDao<MsgDataDao> {
	
	private EntityManager entityManager;
	
	public MsgDataDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public MsgDataDaoImpl(EntityManager manager) {
		entityManager = manager;
	}

	@Override
	public List<MsgData> getAll() {
		
		return entityManager.createQuery("from MsgData").getResultList();
	}

	@Override
	public MsgData findById(long id) {
		
		return (MsgData)entityManager.createQuery("from MsgData where id = " + id).getSingleResult();
	}
	
}
