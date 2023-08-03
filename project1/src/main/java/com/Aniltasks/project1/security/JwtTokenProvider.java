package com.Aniltasks.project1.security;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.Aniltasks.project1.exceptions.APIException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
	
	
	public String generateToken(Authentication authentication) {
		String email = authentication.getName();
		
		 Date currentDate = new Date();
		 /*
		  * token will expired after on hour from current time
		  * 3600000 milliseconds=60 minutes=1 hour 
		  */
		 Date expirydate=new Date(currentDate.getTime()+3600000); //milliseconds 
		 
		 return Jwts.builder()
		 .setSubject(email)
		 .setIssuedAt(currentDate)
		 .setExpiration(expirydate)
		 .signWith(SignatureAlgorithm.HS512, "JwtSecretKey")   //with security key only we can decode the token
		 .compact();
	}
	
	public String getEmailFromToken(String token) {
		Claims body = Jwts.parser().setSigningKey("JwtSecretKey")
		.parseClaimsJws(token).getBody();
		return body.getSubject();                //we store email in the subject
	}
	/**
	 * checking whether token is valid or not
	 * @param token
	 * @return true when token is validated
	 * otherwise return false
	 */
	public boolean validateToken(String token) {
		try{
			Jwts.parser().setSigningKey("JwtSecretKey")
			.parseClaimsJws(token);
			return  true;
		}catch(Exception e) {
			throw new APIException("token issue :"+ e.getMessage());
		}
	}

}
