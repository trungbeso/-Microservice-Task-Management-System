package com.taskmanagement.controller;

import com.taskmanagement.model.Submission;
import com.taskmanagement.model.UserDto;
import com.taskmanagement.service.ISubmissionService;
import com.taskmanagement.service.ITaskService;
import com.taskmanagement.service.IUserService;
import com.taskmanagement.service.SubmissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/submissions")
public class SubmissionController {

	private final ISubmissionService submissionService;
	private final ITaskService taskService;
	private final IUserService userService;

	public SubmissionController(SubmissionService submissionService, ITaskService taskService, IUserService userService) {
		this.submissionService = submissionService;
		this.taskService = taskService;
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<Submission> submitTask(
		  @RequestParam Long taskId,
		  @RequestParam String githubRepo,
		  @RequestHeader("Authorization") String jwt) throws Exception {
		UserDto user = userService.getUserProfile(jwt);
		Submission submission = submissionService.submitTask(taskId, githubRepo, user.getId(), jwt);
		return new ResponseEntity<>(submission, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Submission> getSubmissionById(
		  @PathVariable Long id,
		  @RequestHeader("Authorization") String jwt) throws Exception {
		UserDto user = userService.getUserProfile(jwt);
		Submission submission = submissionService.getTaskSubmissionById(id);
		return new ResponseEntity<>(submission, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<List<Submission>> getAllSubmissions(
		  @RequestHeader("Authorization") String jwt) throws Exception {
		UserDto user = userService.getUserProfile(jwt);
		List<Submission> submissionList = submissionService.getAllTaskSubmissions();
		return new ResponseEntity<>(submissionList, HttpStatus.OK);
	}


	@GetMapping("/task/{taskId}")
	public ResponseEntity<List<Submission>> getAllSubmissions(
		  @PathVariable Long taskId,
		  @RequestHeader("Authorization") String jwt) throws Exception {
		UserDto user = userService.getUserProfile(jwt);
		List<Submission> submissionList = submissionService.getTaskSubmissionsByTaskId(taskId);
		return new ResponseEntity<>(submissionList, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Submission> acceptOrDeclineSubmission(
		  @PathVariable Long id,
		  @RequestParam("status") String status,
		  @RequestHeader("Authorization") String jwt) throws Exception {
		UserDto user = userService.getUserProfile(jwt);
		Submission submission = submissionService.acceptDeclineSubmission(id, status);
		return new ResponseEntity<>(submission, HttpStatus.OK);
	}


}
