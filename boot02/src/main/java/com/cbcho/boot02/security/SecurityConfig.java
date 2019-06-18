package com.cbcho.boot02.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import lombok.extern.java.Log;

@Log
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	UsersService userService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//super.configure(http);
		log.info("security config...");
		http
			.authorizeRequests()
				//.antMatchers("/guest/**").permitAll()
				//.antMatchers("/manager/**").hasAnyRole("MANAGER")
				//.antMatchers("/admin/**").hasAnyRole("ADMIN")
				.antMatchers("/boards/list").permitAll()
				.antMatchers("/boards/register").hasAnyRole("BASIC", "MANAGER", "ADMIN")
			.and() // 로그인 처리
				.formLogin()
					.loginPage("/login")
					.successHandler(new LoginSuccessHandler()) 
			.and() // 접근 없음 페이지 처리
				.exceptionHandling()
					.accessDeniedPage("/accessDenied")
			.and() // 로그아웃
				.logout()
					.logoutUrl("/logout")
					.invalidateHttpSession(true)
			.and() // remember-me
				.rememberMe().key("boot02")
				.userDetailsService(userService)
				.tokenRepository(getJDBCRepository())
				.tokenValiditySeconds(60*60*24); 
	}
	
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		log.info("build Auth global..."); 
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}
	
	/*
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		log.info("build Auth global..."); 
		
		auth.inMemoryAuthentication()
			.withUser("manager")
			.password("{noop}1111")
			.roles("MANAGER");
		
	}
	*/
	
	/*
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		log.info("build Auth global..."); 
	
		String query1 = "SELECT uid username, upw password, true enabled FROM tbl_members WHERE uid=?";
		String query2 = "SELECT member uid, role_name role FROM tbl_member_roles WHERE member = ?";
		
		auth
			.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery(query1)
			.rolePrefix("ROLE_")
			.authoritiesByUsernameQuery(query2)
			.passwordEncoder(passwordEncoder()); 
	}
	*/
	//////////////////////////////////////////////////////////////////////////////
	private PersistentTokenRepository getJDBCRepository() {
		
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		
		return repo;
	}

	//////////////////////////////////////////////////////////////////////////////
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
}
