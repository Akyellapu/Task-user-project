package com.Aniltasks.project1.serviceimpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Aniltasks.project1.entities.Task;
import com.Aniltasks.project1.entities.User;
import com.Aniltasks.project1.payload.TaskDTO;
import com.Aniltasks.project1.repositories.TaskRepository;
import com.Aniltasks.project1.repositories.UserRepository;
import com.Aniltasks.project1.services.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public TaskDTO saveTask(long userId, TaskDTO taskdto) {
		User newUser = userRepository.findById(userId).get();
		/*
		 * convert taskDto into task entity using modelMappeer 
		 * reason: for saving task
		 * into task repository through only task entity class
		 */
		Task taskEntity = modelMapper.map(taskdto, Task.class);
		taskEntity.setUsers(newUser);
		return modelMapper.map(taskEntity, TaskDTO.class);
	}

	@Override
	public List<TaskDTO> getAllTasks(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
