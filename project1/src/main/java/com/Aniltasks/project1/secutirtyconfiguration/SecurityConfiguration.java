package com.Aniltasks.project1.secutirtyconfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	/*
	 * authenticationManager internally validates the password in encoder
	 * but we stored password in non encoder mode
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/*
	 * create two beans SecurityFilterchain and  SecurityAuthenticationManager
	 */
	
	//  throws exception if user enters invalid details
	/*
	 * we need to disable CSRF for my we may pass request to third party
	 * applications like if we call REACTJS from backend
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.csrf().disable()		//cross site request forgery
		.authorizeRequests() //SecurityFilterChain -use full for  authorize all requests
		.antMatchers("/api/user/**").permitAll()  //not to restrict post method with authentication
		.anyRequest()		 // it is for authorize any request
		.authenticated();	//after authorize authenticate the request
		
		return http.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
		
	}

}
