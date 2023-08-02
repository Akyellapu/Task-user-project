package com.Aniltasks.project1.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Aniltasks.project1.entities.Users;
import com.Aniltasks.project1.exceptions.UserNotFoundException;
import com.Aniltasks.project1.repositories.UserRepository;


@Service
public class UserSecurityDetails implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	/*
	 * loading the user by UserName
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Users user = userRepository.findByEmail(email).orElseThrow(
				()->new UserNotFoundException(String.format("user with email: %s not found", email)));
		
		Set<String> roles=new HashSet<>();
		roles.add("admin");
		return new User(user.getEmail(), user.getPassword(),userAuthority(roles));
	}
	
	private Collection<? extends GrantedAuthority> userAuthority(Set<String> roles) {
		return roles.stream().map(
				role->new SimpleGrantedAuthority(role)
				).collect(Collectors.toList());	
	}

}
