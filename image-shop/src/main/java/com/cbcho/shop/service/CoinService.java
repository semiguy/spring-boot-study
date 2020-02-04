package com.cbcho.shop.service;

import java.util.List;

import com.cbcho.shop.domain.ChargeCoin;
import com.cbcho.shop.domain.PayCoin;

public interface CoinService {
	
	void charge(ChargeCoin chargeCoin) throws Exception;
	// 조회
	List<ChargeCoin> list(int userNo) throws Exception;
	// 사용자의상품구매내역을반환
	List<PayCoin> listPayHistory(int userNo) throws Exception;
}
