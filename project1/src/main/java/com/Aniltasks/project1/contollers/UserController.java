package com.Aniltasks.project1.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Aniltasks.project1.payload.JwtAuthResponse;
import com.Aniltasks.project1.payload.LogInUserDTo;
import com.Aniltasks.project1.payload.UserDTO;
import com.Aniltasks.project1.security.JwtTokenProvider;
import com.Aniltasks.project1.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	//registering the user
	@PostMapping("/createUser")
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDetails) {
		return new ResponseEntity<>(userService.createUser(userDetails),HttpStatus.CREATED);
	}
	
	//login user
	@PostMapping("/userlogin")
	public ResponseEntity<JwtAuthResponse> loginUser(@RequestBody LogInUserDTo loginUserDto) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUserDto.getEmail(),loginUserDto.getPassword()));
				SecurityContextHolder.getContext().setAuthentication(authentication);
				//getting token from generate token method in jwtTokenProvider class
				String generateToken = jwtTokenProvider.generateToken(authentication);
				return ResponseEntity.ok(new JwtAuthResponse(generateToken)); //return token as json object		
	}

}
