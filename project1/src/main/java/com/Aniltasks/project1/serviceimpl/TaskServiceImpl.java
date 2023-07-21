package com.Aniltasks.project1.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Aniltasks.project1.entities.Task;
import com.Aniltasks.project1.entities.User;
import com.Aniltasks.project1.exceptions.APIException;
import com.Aniltasks.project1.exceptions.TaskNotFoundException;
import com.Aniltasks.project1.exceptions.UserNotFoundException;
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
		User newUser = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(String.format("user Id %d not found", userId)));
		/*
		 * convert taskDto into task entity using modelMappeer reason: for saving task
		 * into task repository through only task entity class
		 */
		Task taskEntity = modelMapper.map(taskdto, Task.class);
		taskEntity.setUsers(newUser);
		Task savedTask = taskRepository.save(taskEntity);
		return modelMapper.map(savedTask, TaskDTO.class);

	}

	@Override
	public List<TaskDTO> getAllTasks(long userId) {
		userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(String.format("user Id %d not found", userId)));
		List<Task> allTaks = taskRepository.findAllByUsersId(userId);
		/*
		 * converting list of task entities into list of task DTOs
		 */
		// stream used to map one object to another
		return allTaks.stream()
				.map(task -> modelMapper.map(task, TaskDTO.class)// converting each task entity into task
																				// DTO
		).collect(Collectors.toList());
	}

	@Override
	public TaskDTO getIndividualUserTask(long userId, long taskId) {
		User newUser=userRepository.findById(userId)
		.orElseThrow(() -> new UserNotFoundException(String.format("user Id %d not found", userId)));
		
		Task newTask = taskRepository.findById(taskId).orElseThrow(
				()->new TaskNotFoundException(String.format("task id %d not found", taskId)));
		
		if(newUser.getId()!=newTask.getUsers().getId()) {
			throw new APIException(String.format("task id %d is not belong to user Id %d ", taskId,userId));
		}
		
		return modelMapper.map(newTask, TaskDTO.class);
	}

}
