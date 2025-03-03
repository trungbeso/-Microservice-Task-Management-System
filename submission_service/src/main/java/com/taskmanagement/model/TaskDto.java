package com.taskmanagement.model;

import com.taskmanagement.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskDto {
	private Long id;

	private String title;

	private String description;

	private String image;

	private Long assignedUserId;

	private List<String> tags = new ArrayList<>();

	private TaskStatus status;

	private LocalDateTime deadline;

	private LocalDateTime createAt;


	public TaskDto(Long id, String title, String description, String image, Long assignedUserId, List<String> tags, TaskStatus status, LocalDateTime deadline, LocalDateTime createAt) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.image = image;
		this.assignedUserId = assignedUserId;
		this.tags = tags;
		this.status = status;
		this.deadline = deadline;
		this.createAt = createAt;
	}

	public TaskDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getAssignedUserId() {
		return assignedUserId;
	}

	public void setAssignedUserId(Long assignedUserId) {
		this.assignedUserId = assignedUserId;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public LocalDateTime getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
}
