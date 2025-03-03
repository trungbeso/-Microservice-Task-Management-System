package com.taskmanagement.service;

import com.taskmanagement.model.Submission;

import java.util.List;

public interface ISubmissionService {

	Submission submitTask(Long taskId, String githubRepo, Long userId,String jwt) throws Exception;

	Submission getTaskSubmissionById(Long submissionId);

	List<Submission> getAllTaskSubmissions();

	List<Submission> getTaskSubmissionsByTaskId(Long taskId);

	Submission acceptDeclineSubmission(Long id, String status) throws Exception;

}
