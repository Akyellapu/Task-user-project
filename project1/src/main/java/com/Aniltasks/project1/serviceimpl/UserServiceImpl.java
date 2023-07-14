package com.Aniltasks.project1.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Aniltasks.project1.entities.User;
import com.Aniltasks.project1.payload.UserDTO;
import com.Aniltasks.project1.repositories.UserRepository;
import com.Aniltasks.project1.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private  UserRepository userRepositorydetails;
	
	@Override
	public UserDTO createUser(UserDTO userdetails) {
		/*
		 * convert user_dto to user
		 */
		User userdtoToUserEntity = userdtoToUserEntity(userdetails);
		User save = userRepositorydetails.save(userdtoToUserEntity);
		return entityToUserdto(save);
	}
	/*
	 * method to convert user_dto to user entity
	 */
	private User userdtoToUserEntity(UserDTO userdetails) {
		User user=new User();
		user.setEmail(userdetails.getEmail());
		user.setName(userdetails.getName());
		user.setPassword(userdetails.getPassword());
		return user;
	}
	/*method to convert user_dto to user entity
	 * 
	 */
	private UserDTO entityToUserdto(User userDetails) {
		UserDTO userDTO=new UserDTO();
		userDTO.setId(userDetails.getId());
		userDTO.setEmail(userDetails.getEmail());
		userDTO.setName(userDetails.getName());
		userDTO.setPassword(userDetails.getPassword());
		return userDTO;
	}

}
