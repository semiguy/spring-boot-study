package com.cbcho.boot.ch7.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cbcho.boot.ch7.component.MyDataBean;

@Configuration
public class MyBootAppConfig {
	
	@Bean
	MyDataBean myDataBean() {
		return new MyDataBean();
	}
}
