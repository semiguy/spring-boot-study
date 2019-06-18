package com.cbcho.boot02.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cbcho.boot02.interceptor.LoginCheckInterceptor;

import lombok.extern.java.Log;

@Configuration
@Log
public class InterceptorConfig implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		log.info("addInterceptors()..............................");
		registry.addInterceptor(new LoginCheckInterceptor()).addPathPatterns("/login");
		
		WebMvcConfigurer.super.addInterceptors(registry);
	}
}
