package com.cbcho.aop.common.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public class TimeCheckerAdvice {
	
	@Before("execution(* com.cbcho.aop.service.BoardService*.*(..))")
	public void log(JoinPoint jp) {
		
		// 프락시가 입혀지기 전의 원본 대상 객체를 가져온다.
		Object targetObject = jp.getTarget();
		log.info("targetObject=" + targetObject);
		
		// 프락시를 가져온다.
		Object thisObject = jp.getThis();
		log.info("thisObject=" + thisObject);
		
		// 인수를 가져온다.
		Object[] args = jp.getArgs();
		log.info("args length=" + args.length); 
	}
	
	@Around("execution(* com.cbcho.aop.service.BoardService*.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		
		long startTime = System.currentTimeMillis();
		log.info(Arrays.toString(pjp.getArgs()));
		
		Object result = pjp.proceed();
		
		long endTime = System.currentTimeMillis();
		log.info(pjp.getSignature().getName() + " : " + (endTime - startTime));
		
		return result;
	}
}
