package com.cbcho.shop.exception;

public class NotMyItemException extends Exception {
	
	// 본인 상품 예외 클래스
	public NotMyItemException(String msg) {
		super(msg);
	}
}
