package com.cbcho.shop.mapper;

import java.util.List;

import com.cbcho.shop.domain.ChargeCoin;
import com.cbcho.shop.domain.PayCoin;

public interface CoinMapper {
	
	void create(ChargeCoin chargeCoin) throws Exception;
	void charge(ChargeCoin chargeCoin) throws Exception;
	List<ChargeCoin> list(int userNo) throws Exception;
	// 구매내역생성
	void createPayHistory(PayCoin payCoin) throws Exception;
	// 상품구매에대한코인지급처리
	void pay(PayCoin payCoin) throws Exception;
	// 구매내역
	List<PayCoin> listPayHistory(int userNo) throws Exception;
}
