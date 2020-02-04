package com.cbcho.shop.common.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cbcho.shop.common.domain.AccessLog;
import com.cbcho.shop.common.service.AccessLogService;
import com.cbcho.shop.common.util.NetUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccessLoggingInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private AccessLogService accessLogService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//return super.preHandle(request, response, handler);
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		//super.postHandle(request, response, handler, modelAndView);
		String requestUri = request.getRequestURI();
		String remoteAddr = NetUtils.getIp(request);
		
		log.info("requestUri : " + requestUri);
		log.info("remoteAddr : " + remoteAddr);
		
		if(handler instanceof HandlerMethod) {
			
			HandlerMethod handlerMethod = (HandlerMethod)handler;
			Method method = handlerMethod.getMethod();
			
			Class clazz = method.getDeclaringClass();
			
			String className = clazz.getName();
			String classSimpleName = clazz.getSimpleName();
			String methodName = method.getName();
			
			log.info("[ACCESS CONTROLLER]" + className + "." + methodName);
			
			AccessLog accessLog = new AccessLog();
			
			accessLog.setRequestUri(requestUri);
			accessLog.setRemoteAddr(remoteAddr);
			accessLog.setClassName(className);
			accessLog.setClassSimpleName(classSimpleName);
			accessLog.setMethodName(methodName);
			
			accessLogService.register(accessLog);
		} else {
			log.info("handler : " + handler); 
		}
	}
}
