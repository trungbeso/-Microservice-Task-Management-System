package com.taskmanagement.service;

import com.taskmanagement.dtos.UserDto;
import com.taskmanagement.model.Task;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "TASK-SERVICE", url = "http://localhost:8082")
public class TaskService {

	@GetMapping("/{id}")
	public ResponseEntity<Task> getTaskById(@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {
		UserDto user = userService.getUserProfile(jwt);
		Task task = taskService.getTaskById(id);
		return new ResponseEntity<>(task, HttpStatus.OK);
	}
}
