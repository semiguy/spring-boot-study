package com.cbcho.aop.common.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public class ServiceLoggerAdvice {
	
	@Before("execution(* com.cbcho.aop.service.BoardService*.*(..))")
	public void startLog(JoinPoint jp) {
		log.info("startLog");
		log.info("startLog:" + jp.getSignature());
		log.info("startLog:" + Arrays.toString(jp.getArgs())); 
	}
	
	@AfterReturning("execution(* com.cbcho.aop.service.BoardService*.*(..))")
	public void logReturning(JoinPoint jp) {
		log.info("logReturning");
		log.info("logReturning:" + jp.getSignature()); 
	}
	
	@AfterThrowing(pointcut = "execution(* com.cbcho.aop.service.BoardService*.*(..))", throwing = "e")
	public void logException(JoinPoint jp, Exception e) {
		log.info("logException");
		log.info("logException:" + jp.getSignature()); 
		log.info("logException:" + e); 
	}
	
	@After("execution(* com.cbcho.aop.service.BoardService*.*(..))")
	public void endLog(JoinPoint jp) {
		log.info("endLog");
		log.info("endLog:" + jp.getSignature()); 
		log.info("endLog:" + Arrays.toString(jp.getArgs())); 
	}
	
	
}
