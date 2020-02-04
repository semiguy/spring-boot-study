package com.cbcho.shop.exception;

public class NotEnoughCoinException extends Exception {
	
	// 코인 부족 예외 클래스
	public NotEnoughCoinException(String msg) {
		super(msg);
	}
}
