package com.cbcho.boot.ch6.dao;

import java.util.List;

import com.cbcho.boot.ch6.model.MsgData;

public interface MsgDataDao<T> {
	
	public List<MsgData> getAll();
	public MsgData findById(long id);
}
