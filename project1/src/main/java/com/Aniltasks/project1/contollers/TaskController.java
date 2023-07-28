package com.Aniltasks.project1.contollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

	// save all tasksTaskDTO
	@PostMapping("{userId}/saveTask")
	public ResponseEntity<TaskDTO> saveTask(@PathVariable(name = "userId") long userId,
			@RequestBody TaskDTO taskDTODetails) {
		return new ResponseEntity<>(taskService.saveTask(userId, taskDTODetails), HttpStatus.CREATED);
	}

	// get all tasks
	/*
	 * variable name in Get mapping i.e userId and name in PathVariable must be same
	 * i.e userId variable name in Get mapping i.e userId and variable name in
	 * PathVariable may not be the same i.e id
	 */
	@GetMapping("{userId}/getAllTasks")
	public ResponseEntity<List<TaskDTO>> getAllTasks(@PathVariable(name = "userId") long id) {
		return new ResponseEntity<>(taskService.getAllTasks(id), HttpStatus.OK);// status OK for Successful fetch

	}

	// get individual task
	@GetMapping("{userId}/getIndiviudualTask/{taskId}")
	public ResponseEntity<TaskDTO> getIndividualTaskByUserIdAndTaskId(@PathVariable(name = "userId") long uid,
			@PathVariable(name = "taskId") long tId) {
		return new ResponseEntity<>(taskService.getIndividualUserTask(uid, tId), HttpStatus.OK);
	}

	// delete individual task
	@DeleteMapping("{userId}/deleteIndiviudualTask/{taskId}")
	public ResponseEntity<String> deleteTask(@PathVariable(name = "userId") long uid,
			@PathVariable(name = "taskId") long tId) {
		taskService.deleteTask(uid, tId);
		return new ResponseEntity<>("task deleted successfully", HttpStatus.OK);
	}

}
