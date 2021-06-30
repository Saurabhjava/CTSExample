package com.cts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cts.service.MyUserDetailService;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
	@Autowired
	private MyUserDetailService uService;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("==========Configurer get called=============");
		auth.userDetailsService(uService);
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
}
