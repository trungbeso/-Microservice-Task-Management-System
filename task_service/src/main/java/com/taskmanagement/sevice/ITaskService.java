package com.taskmanagement.sevice;

import com.taskmanagement.enums.TaskStatus;
import com.taskmanagement.model.Task;

import java.util.List;

public interface ITaskService {
	Task createTask(Task task, String requesterRole) throws Exception;

	Task getTaskById(Long id) throws Exception;

	List<Task> getAllTasks(TaskStatus status);

	Task updateTask(Long id, Task updateTask, Long userId) throws Exception;

	void deleteTask(Long id);

	Task assignedToUser(Long taskId, Long userId) throws Exception;

	List<Task> assignedUsersTask(Long userId, TaskStatus status);

	Task completedTask(Long taskId) throws Exception;
}
