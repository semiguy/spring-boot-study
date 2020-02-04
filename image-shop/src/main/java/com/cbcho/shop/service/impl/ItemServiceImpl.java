package com.cbcho.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbcho.shop.domain.Item;
import com.cbcho.shop.mapper.ItemMapper;
import com.cbcho.shop.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	private final ItemMapper itemMapper;
	
	@Autowired
	public ItemServiceImpl(ItemMapper itemMapper) {
		
		this.itemMapper = itemMapper;
	}

	@Override
	public void register(Item item) throws Exception {
		
		itemMapper.create(item);
	}

	@Override
	public List<Item> list() throws Exception {
		
		return itemMapper.list();
	}

	@Override
	public Item read(Integer itemId) throws Exception {
		
		return itemMapper.read(itemId);
	}

	@Override
	public void modify(Item item) throws Exception {
		
		itemMapper.update(item);
		
	}

	@Override
	public void remove(Integer itemId) throws Exception {
		
		itemMapper.delete(itemId);
	}

	@Override
	public String getPreview(Integer itemId) throws Exception {
		
		return itemMapper.getPreview(itemId);
	}

	@Override
	public String getPicture(Integer itemId) throws Exception {
		
		return itemMapper.getPicture(itemId); 
	}
	
	
}
