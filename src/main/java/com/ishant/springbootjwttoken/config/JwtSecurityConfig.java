package com.ishant.springbootjwttoken.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ishant.springbootjwttoken.security.JwtAuthenticationEntryPoint;
import com.ishant.springbootjwttoken.security.JwtAuthenticationProvider;
import com.ishant.springbootjwttoken.security.JwtAuthenticationTokenFilter;
import com.ishant.springbootjwttoken.security.JwtSuccessHandler;

@EnableGlobalMethodSecurity(prePostEnabled=true)
@EnableWebSecurity
@Configuration
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter{

	/***
	 * In spring Whenever we implement any security mechanisn 
	 * we need to extend the WebSecurityConfureAdapter 
	 * to overirde the spring default security properties 
	 * **/
	
	@Autowired
	private JwtAuthenticationProvider authenticationProvider;
	
	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;
	
	
	/**
	 * JWTAuthenicationTOkenFilter is our custom using which 
	 * we will be overriding the Filter  toproperties and implement it 

	 * **/
	@Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilter() {
		JwtAuthenticationTokenFilter tokenFilter = new JwtAuthenticationTokenFilter();
		tokenFilter.setAuthenticationManager(authenticationManager());
		tokenFilter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
		return tokenFilter;
	}	
	
	/*
	 * Authentication manager need to be overriden beacuse 
	 * we want the spring to use this way to authrouse the coming request
	 * **/
	@Bean
	public AuthenticationManager authenticationManager() {
		List<AuthenticationProvider> authenticationProviderList = new ArrayList<AuthenticationProvider>();
		authenticationProviderList.add(authenticationProvider);
		return new ProviderManager(authenticationProviderList);
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception{
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("**/rest/**").authenticated()
		.and()
		.exceptionHandling().authenticationEntryPoint(entryPoint)
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		
		http.headers().cacheControl();
	}
	

	
}
