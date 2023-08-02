package com.Aniltasks.project1.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Aniltasks.project1.entities.Users;
import com.Aniltasks.project1.payload.UserDTO;
import com.Aniltasks.project1.repositories.UserRepository;
import com.Aniltasks.project1.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private  UserRepository userRepositorydetails;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDTO createUser(UserDTO userdetails) {
		/*
		 * authenticationManager validates the password in encoder
		 * but we enter password normally so we have to encode the password
		 * now authenticationManager decodes the password and validates it
		 */
		userdetails.setPassword(passwordEncoder.encode(userdetails.getPassword()));
		//convert user DTO to user entity
		Users userdtoToUserEntity = userdtoToUserEntity(userdetails);
		Users save = userRepositorydetails.save(userdtoToUserEntity);
		return entityToUserdto(save);
	}
	/*
	 * method to convert user_dto to user entity
	 */
	private Users userdtoToUserEntity(UserDTO userdetails) {
		Users user=new Users();
		user.setEmail(userdetails.getEmail());
		user.setName(userdetails.getName());
		user.setPassword(userdetails.getPassword());
		return user;
	}
	/*method to convert user_dto to user entity
	 * 
	 */
	private UserDTO entityToUserdto(Users userDetails) {
		UserDTO userDTO=new UserDTO();
		userDTO.setId(userDetails.getId());
		userDTO.setEmail(userDetails.getEmail());
		userDTO.setName(userDetails.getName());
		userDTO.setPassword(userDetails.getPassword());
		return userDTO;
	}

}
