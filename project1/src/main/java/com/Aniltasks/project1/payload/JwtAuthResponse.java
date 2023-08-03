package com.Aniltasks.project1.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthResponse {
	
	private String token;
	
	private String tokenType="Bearer";  //always token type is bearer
	
	public JwtAuthResponse(String token) {
		this.token=token;
	}

}
