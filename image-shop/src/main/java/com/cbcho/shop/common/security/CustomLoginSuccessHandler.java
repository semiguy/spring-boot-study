package com.cbcho.shop.common.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.cbcho.shop.common.domain.LoginLog;
import com.cbcho.shop.common.security.domain.CustomUser;
import com.cbcho.shop.common.service.LoginLogService;
import com.cbcho.shop.common.util.NetUtils;
import com.cbcho.shop.domain.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
	@Autowired
	private LoginLogService loginLogService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		
		CustomUser customUser = (CustomUser)authentication.getPrincipal();
		Member member = customUser.getMember();
		
		log.info("Userid = " + member.getUserId()); 
		
		// 로그인에 성공한 사용자의 IP정보와 사용자 정보를 로깅 처리
		String remoteAddr = NetUtils.getIp(request);
		
		log.info("remoteAddr = " + remoteAddr);
		
		LoginLog loginLog = new LoginLog();
		loginLog.setUserNo(member.getUserNo());
		loginLog.setUserId(member.getUserId());
		loginLog.setRemoteAddr(remoteAddr);
		
		try {
			loginLogService.register(loginLog); 
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	
		super.onAuthenticationSuccess(request, response, authentication);
	}
}
