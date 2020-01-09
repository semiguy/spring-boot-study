package com.cbcho.mybatis.common.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccessLogginInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//return super.preHandle(request, response, handler);
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		String requestURL = request.getRequestURI();
		
		log.info("requestURL:" + requestURL);
		
		HandlerMethod handleMethod = (HandlerMethod)handler;
		
		if(handleMethod != null) {			
			Method method = handleMethod.getMethod();
			
			Class<?> clazz = method.getDeclaringClass();
			
			String className = clazz.getName();
			String classSimpleName = clazz.getSimpleName();
			String methodName = method.getName();
			
			log.info("[ACCESS CONTROLLER]" + className + "." + methodName); 
		}
		
		super.postHandle(request, response, handler, modelAndView);
	}
}
