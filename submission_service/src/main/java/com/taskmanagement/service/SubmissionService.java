package com.taskmanagement.service;

import com.taskmanagement.model.Submission;
import com.taskmanagement.model.TaskDto;
import com.taskmanagement.model.UserDto;
import com.taskmanagement.repository.ISubmissionRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubmissionService implements ISubmissionService{

	private final ISubmissionRepository submissionRepository;
	private final ITaskService taskService;
	private final IUserService userService;

	public SubmissionService(ISubmissionRepository submissionRepository, ITaskService taskService, IUserService userService) {
		this.submissionRepository = submissionRepository;
		this.taskService = taskService;
		this.userService = userService;
	}

	@Override
	public Submission submitTask(Long taskId, String githubRepo, Long userId, String jwt) throws Exception {
		TaskDto task = taskService.getTaskById(taskId, jwt);
//		UserDto user = userService.getUserProfile(jwt)
		if (task != null) {
			Submission submission = new Submission();
			submission.setTaskId(taskId);
			submission.setUserId(userId);
			submission.setGithubRepo(githubRepo);
			submission.setSubmissionTime(LocalDateTime.now());
			return submissionRepository.save(submission);
		}
		throw new Exception("Task not found with id " + taskId);
	}

	@Override
	public Submission getTaskSubmissionById(Long submissionId) {
		return submissionRepository.findById(submissionId).orElseThrow(() -> new RuntimeException("Task submission not " +
			  " found with id " + submissionId));
	}

	@Override
	public List<Submission> getAllTaskSubmissions() {
		return submissionRepository.findAll();
	}

	@Override
	public List<Submission> getTaskSubmissionsByTaskId(Long taskId) {
		return submissionRepository.findByTaskId(taskId);
	}

	@Override
	public Submission acceptDeclineSubmission(Long id, String status) throws Exception {
		Submission submission = getTaskSubmissionById(id);
		submission.setStatus(status);
		if (status.equals("ACCEPT")) {
			taskService.completeTask(submission.getTaskId());
		}
		return submissionRepository.save(submission);
	}
}
