package com.Aniltasks.project1.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;


/*
 * it is a class for handle APIs with authenticated token
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

		@Autowired
		private JwtTokenProvider jwtTokenProvider;
		
		@Autowired
		private UserSecurityDetails userSecurityDetails;
		
		
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//get the token from header
		String token=getToken(request);
		//check the token valid or not
		
		if(StringUtils.hasText(token)&&jwtTokenProvider.validateToken(token)) {
			String emailFromToken = jwtTokenProvider.getEmailFromToken(token);
			//load the user 
			UserDetails userDetails = userSecurityDetails.loadUserByUsername(emailFromToken);
			UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			//set authentication
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	private String getToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		
		//check whether token is there are not
		
		if(StringUtils.hasText(token)&&token.startsWith("Bearer ")) {
			return token.substring(7, token.length());
		}
		return null;
	}

}
