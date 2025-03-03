package com.taskmanagement.controller;

import com.taskmanagement.dtos.UserDto;
import com.taskmanagement.enums.TaskStatus;
import com.taskmanagement.model.Task;
import com.taskmanagement.sevice.ITaskService;
import com.taskmanagement.sevice.IUserService;
import com.taskmanagement.sevice.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {

	private final ITaskService taskService;
	private final IUserService userService;

	public TaskController(TaskService taskService, IUserService userService) {
		this.taskService = taskService;
		this.userService = userService;
	}

	@PostMapping("/create")
	public ResponseEntity<Task> createTask(@RequestBody Task task, @RequestHeader("Authorization") String jwt) throws Exception {
		UserDto user = userService.getUserProfile(jwt);
		Task createTask = taskService.createTask(task, user.getRole());
		return new ResponseEntity<>(createTask, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Task> getTaskById(@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {
		UserDto user = userService.getUserProfile(jwt);
		Task task = taskService.getTaskById(id);
		return new ResponseEntity<>(task, HttpStatus.OK);
	}

	@GetMapping("/user")
	public ResponseEntity<List<Task>> getAssignedUserTask(@RequestHeader("Authorization") String jwt, @RequestParam(required = false) TaskStatus status) throws Exception {
		UserDto user = userService.getUserProfile(jwt);
		List<Task> tasks = taskService.assignedUsersTask(user.getId(), status);
		return new ResponseEntity<>(tasks, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<List<Task>> getAllTask(@RequestHeader("Authorization") String jwt, @RequestParam(required = false) TaskStatus status) throws Exception {
		UserDto user = userService.getUserProfile(jwt);
		List<Task> tasks = taskService.getAllTasks(status);
		return new ResponseEntity<>(tasks, HttpStatus.OK);
	}

	@PutMapping("/{id}/user/{userId}/assigned")
	public ResponseEntity<Task> assignedTaskToUser(@RequestHeader("Authorization") String jwt, @PathVariable Long id, @PathVariable Long userId) throws Exception {
		UserDto user = userService.getUserProfile(jwt);
		Task task = taskService.assignedToUser(id, userId);
		return new ResponseEntity<>(task, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Task> updateTask(@RequestHeader("Authorization") String jwt, @PathVariable Long id, @RequestBody Task request) throws Exception {
		UserDto user = userService.getUserProfile(jwt);
		Task task = taskService.updateTask(id, request, user.getId());
		return new ResponseEntity<>(task, HttpStatus.OK);
	}

	@PutMapping("/complete/{id}")
	public ResponseEntity<Task> completeTask(@PathVariable Long id) throws Exception {
		Task task = taskService.completedTask(id);
		return new ResponseEntity<>(task, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long id) throws Exception {
		taskService.deleteTask(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}


}
