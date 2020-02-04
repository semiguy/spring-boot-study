package com.cbcho.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbcho.shop.domain.ChargeCoin;
import com.cbcho.shop.domain.PayCoin;
import com.cbcho.shop.mapper.CoinMapper;
import com.cbcho.shop.service.CoinService;

@Service
public class CoinServiceImpl implements CoinService {
	
	private final CoinMapper coinMapper;
	
	@Autowired
	public CoinServiceImpl(CoinMapper coinMapper) {
		
		this.coinMapper = coinMapper;
	}
	
	@Transactional
	@Override
	public void charge(ChargeCoin chargeCoin) throws Exception {
		
		coinMapper.charge(chargeCoin);
		coinMapper.create(chargeCoin);
	}

	@Override
	public List<ChargeCoin> list(int userNo) throws Exception {
		
		return coinMapper.list(userNo);
	}
	
	// 사용자의상품구매내역을반환
	@Override
	public List<PayCoin> listPayHistory(int userNo) throws Exception {
		
		return coinMapper.listPayHistory(userNo);
	}
	
	
}
