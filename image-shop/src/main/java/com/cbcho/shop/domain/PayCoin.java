package com.cbcho.shop.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PayCoin {
	
	private int historyNo;
	private int userNo;
	private int itemId;
	private String itemName;
	private int amount;
	private Date regDate;
}
