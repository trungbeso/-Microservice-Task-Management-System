package com.taskmanagement.repository;

import com.taskmanagement.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISubmissionRepository extends JpaRepository<Submission, Long> {
	List<Submission> findByTaskId(Long taskId);
}
