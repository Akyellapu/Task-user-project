package com.Aniltasks.project1.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Aniltasks.project1.payload.TaskDTO;
import com.Aniltasks.project1.services.TaskService;

@RestController
@RequestMapping("/api/task")
public class TaskController {

	@Autowired
	private TaskService taskService;

	// save all tasks
	@PostMapping("{userId}/saveTask")
	public ResponseEntity<TaskDTO> saveTask(@PathVariable(name = "userId") long userId,
			@RequestBody TaskDTO TaskDTODetails) {
		return new ResponseEntity<>(taskService.saveTask(userId, TaskDTODetails), HttpStatus.CREATED);
	}

	// get all tasks

	// get individual task

	// delete individual task

}
