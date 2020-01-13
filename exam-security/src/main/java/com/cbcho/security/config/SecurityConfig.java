package com.cbcho.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.cbcho.security.common.security.CustomAccessDeniedHandler;
import com.cbcho.security.common.security.CustomLoginSuccessHandler;
import com.cbcho.security.common.security.CustomNoOpPasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//super.configure(http);
		log.info("security config ...");
		
		http
			.authorizeRequests()
				.antMatchers("/board/list").permitAll()
				.antMatchers("/board/register").hasRole("MEMBER")
				.antMatchers("/notice/list").permitAll()
				.antMatchers("/notice/register").hasRole("ADMIN")
			.and()
				.formLogin() // 폼 기반 인증 기능을 사용한다.
					.loginPage("/login")
					.successHandler(createAuthenticationSuccessHandler())
			.and()
				.exceptionHandling()
					//.accessDeniedPage("/accessError")
					.accessDeniedHandler(createAccessDeniedHandler())
			.and()
				.logout()
					.logoutUrl("/logout")
					.invalidateHttpSession(true);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//super.configure(auth);
		// 지정된 아이디와 패스워드로 로그인이 가능하도록 설정한다.
		/*
		auth.inMemoryAuthentication()
			.withUser("member")
			.password("{noop}1234")
			.roles("MEMBER");
		
		auth.inMemoryAuthentication()
			.withUser("admin")
			.password("{noop}1234")
			.roles("ADMIN");
		*/
		
		// 스프링 시큐리티가 원하는 결과를 반환하는 쿼리를 작성한다.
		String query1 = "SELECT user_id, user_pw, enabled FROM member WHERE user_id=?";
		String query2 = "SELECT b.user_id, a.auth FROM member_auth a, member b WHERE a.user_no=b.user_no AND b.user_id=?";
		
		// JDBC 인증 제공자
		auth.jdbcAuthentication()
			// 데이터 소스를 지정한다.
			.dataSource(dataSource)
			// 작성한 쿼리를 지정한다.
			.usersByUsernameQuery(query1)
			.authoritiesByUsernameQuery(query2)
			// BCryptPasswordEncoder 비밀번호 암호화 처리기를 지정한다.
			.passwordEncoder(createPasswordEncoder());
			// 사용자가 정의한 비밀번호 암호화 처리기를 지정한다.
			//.passwordEncoder(createPasswordEncoder());
		
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////
	@Bean
	public AccessDeniedHandler createAccessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}
	
	@Bean
	public AuthenticationSuccessHandler createAuthenticationSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}
	
	/*
	@Bean
	public PasswordEncoder createPasswordEncoder() {
		return new CustomNoOpPasswordEncoder();
	}
	*/
	
	// 스프링 시큐리티에서 제공되는 BCryptPasswordEncoder 클래스를 빈으로 등록
	@Bean
	public PasswordEncoder createPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
}
