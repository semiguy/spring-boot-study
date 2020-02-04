package com.cbcho.shop.common.domain;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PerformanceLog {
	
	private String signatureName;
	private String signatureTypeName;
	private long durationTime;
	private Date regDate;
}
