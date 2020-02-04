package com.cbcho.shop.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.cbcho.shop.common.security.CustomAccessDeniedHandler;
import com.cbcho.shop.common.security.CustomLoginSuccessHandler;
import com.cbcho.shop.common.security.CustomUserDetailsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//super.configure(http);
		log.info("security config...");
		http
			.formLogin()
				.loginPage("/auth/login")
				.loginProcessingUrl("/login")
				// CustomLoginSuccessHandler를 로그인 성공 처리자로 지정한다.
				.successHandler(createAuthenticationSuccessHandler())
			.and()
				.logout()
					.logoutUrl("/auth/logout")
					.invalidateHttpSession(true)
					// 로그아웃을 하면 자동 로그인에 사용하는 쿠기도 삭제해 주도록 한다.
					.deleteCookies("remember-me", "JSESSION_ID")
			.and()
				.exceptionHandling()
				// CustomAccessDeniedHandler를 접근 거부 처리자로 지정한다.
				.accessDeniedHandler(createAccessDeniedHandler())
			.and()
				// 데이터소스를 지정하고 테이블을 이용해서 기존 로그인 정보를 기록
				.rememberMe()
				.key("cbcho")
				.tokenRepository(createJDBCRepository())
				// 쿠키의 유효시간을 지정한다.
				.tokenValiditySeconds(60 * 60 * 24);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//super.configure(auth);
		auth.userDetailsService(createUserDetailsService()).passwordEncoder(createPasswordEncoder());
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	@Bean
	public UserDetailsService createUserDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Bean
	public PasswordEncoder createPasswordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationSuccessHandler createAuthenticationSuccessHandler() {
		
		return new CustomLoginSuccessHandler();
	}
	
	@Bean
	public AccessDeniedHandler createAccessDeniedHandler() {
		
		return new CustomAccessDeniedHandler();
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////
	private PersistentTokenRepository createJDBCRepository() {
		
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		
		return repo;
	}
}
