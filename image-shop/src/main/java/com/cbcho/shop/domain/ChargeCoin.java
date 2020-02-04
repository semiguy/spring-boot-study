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
public class ChargeCoin {
	
	private int historyNo;
	private int userNo;
	private int amount;
	private Date regDate;
}
