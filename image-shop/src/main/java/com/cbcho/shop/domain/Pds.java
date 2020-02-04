package com.cbcho.shop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Pds {
	
	private Integer itemId;
	private String itemName;
	private Integer viewCnt;
	private String description;
	private String[] files;
	
}
