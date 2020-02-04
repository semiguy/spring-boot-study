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
public class UserItem {
	
	private int userItemNo;
	private int userNo;
	
	private int itemId;
	private String itemName;
	private Integer price;
	private String description;
	private String pictureUrl;
	
	private Date regDate;
}
