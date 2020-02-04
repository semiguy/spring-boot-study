package com.cbcho.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cbcho.shop.common.interceptor.AccessLoggingInterceptor;

@Configuration
public class IntercepterConfig implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		//WebMvcConfigurer.super.addInterceptors(registry);
		registry.addInterceptor(accessLoggingIntercepter())
			.addPathPatterns("/**")
			.excludePathPatterns("/resources/**");
	}
	
	@Bean
	public HandlerInterceptor accessLoggingIntercepter() {
		
		return new AccessLoggingInterceptor();
	}
}
