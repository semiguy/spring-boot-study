package com.cbcho.shop.common.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodeLabelValue {
	
	private String label;
	private String value;
	
	public CodeLabelValue() {
		super();
	}
	
	public CodeLabelValue(String value, String label) {
		
		this.value = value;
		this.label = label;
	}
}
