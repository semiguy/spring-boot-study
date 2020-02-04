package com.cbcho.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbcho.shop.domain.Item;
import com.cbcho.shop.domain.Member;
import com.cbcho.shop.domain.PayCoin;
import com.cbcho.shop.domain.UserItem;
import com.cbcho.shop.exception.NotEnoughCoinException;
import com.cbcho.shop.mapper.CoinMapper;
import com.cbcho.shop.mapper.UserItemMapper;
import com.cbcho.shop.service.UserItemService;

@Service
public class UserItemServiceImpl implements UserItemService {
	
	private final UserItemMapper userItemMapper;
	private final CoinMapper coinMapper;
	
	@Autowired
	public UserItemServiceImpl(UserItemMapper userItemMapper, CoinMapper coinMapper) {
		
		this.userItemMapper = userItemMapper;
		this.coinMapper = coinMapper;
	}
	
	@Transactional
	@Override
	public void register(Member member, Item item) throws Exception {
		
		int userNo = member.getUserNo();
		
		// 사용자의 사용가능한 코인을 가져온다.
		int coin = member.getCoin();
		
		int itemId = item.getItemId();
		int price = item.getPrice();
		
		UserItem userItem = new UserItem();
		userItem.setUserNo(userNo);
		userItem.setItemId(itemId);
		
		// 사용자의 코인이 부족한지 체크.
		if(coin < price) {
			throw new NotEnoughCoinException("There is Not Enough Coin.");
		}
		
		PayCoin payCoin = new PayCoin();
		payCoin.setUserNo(userNo);
		payCoin.setItemId(itemId);
		payCoin.setAmount(price);
		
		coinMapper.pay(payCoin);
		coinMapper.createPayHistory(payCoin);
		
		userItemMapper.create(userItem);
	}

	@Override
	public List<UserItem> list(Integer userNo) throws Exception {
		
		return userItemMapper.list(userNo);
	}
	
	@Override
	public List<UserItem> listAll() throws Exception {
		
		return userItemMapper.listAll();
	}

	@Override
	public UserItem read(Integer userItemNo) throws Exception {
		
		return userItemMapper.read(userItemNo);
	}
	
	
	
}
