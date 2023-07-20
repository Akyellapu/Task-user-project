package com.Aniltasks.project1.services;

import java.util.List;

import com.Aniltasks.project1.payload.TaskDTO;

public interface TaskService {
	
	
	
	public TaskDTO saveTask(long userId,TaskDTO taskdto);
	
	
	public List<TaskDTO> getAllTasks(long userId);
	
	public TaskDTO getIndividualUserTask(long userId, long todoId);

}
