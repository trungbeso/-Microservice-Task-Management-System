package com.taskmanagement.service;

import com.taskmanagement.model.TaskDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "SUBMISSION-SERVICE", url = "http://localhost:8082")
public interface ITaskService {

	@GetMapping("api/v1/tasks/{id}")
	TaskDto getTaskById(
		  @PathVariable Long id,
		  @RequestHeader("Authorization") String jwt) throws Exception;

	@PutMapping("api/v1/tasks/complete/{id}")
	TaskDto completeTask(@PathVariable Long id) throws Exception;
}
