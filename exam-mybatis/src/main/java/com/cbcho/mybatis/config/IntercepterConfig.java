package com.cbcho.mybatis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cbcho.mybatis.common.interceptor.AccessLogginInterceptor;
import com.cbcho.mybatis.common.interceptor.LoginInterceptor;

@Configuration
public class IntercepterConfig implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		// 원하는 URI에 적절한 패턴을 적용하여 인터셉터를 지정한다.
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/users/login");
		registry.addInterceptor(new AccessLogginInterceptor()).addPathPatterns("/**").excludePathPatterns("/resources/**");
		
		WebMvcConfigurer.super.addInterceptors(registry);
	}
}
