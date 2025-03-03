package com.taskmanagement.sevice;

import com.taskmanagement.enums.TaskStatus;
import com.taskmanagement.model.Task;
import com.taskmanagement.repository.ITaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService implements ITaskService {

	private final ITaskRepository taskRepository;

	public TaskService(ITaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public Task createTask(Task task, String requesterRole) throws Exception {
		if (!requesterRole.equals("ROLE_ADMIN")) {
			throw new Exception("Only Admin can create task");
		}
		task.setStatus(TaskStatus.PENDING);
		task.setCreateAt(LocalDateTime.now());
		task = taskRepository.save(task);
		return task;
	}

	@Override
	public Task getTaskById(Long id) throws Exception {
		return taskRepository.findById(id).orElseThrow(() -> new Exception("Task not found with id: " + id));
	}

	@Override
	public List<Task> getAllTasks(TaskStatus status) {
		List<Task> taskList = taskRepository.findAll();
		return taskList
			  .stream()
			  .filter(task -> task.getStatus().name().equalsIgnoreCase(status.toString()) || status == null)
			  .toList();
	}

	@Override
	public Task updateTask(Long id, Task updateTask, Long userId) throws Exception {
		Task existingTask = getTaskById(id);

		if (updateTask.getTitle() != null) {
			existingTask.setTitle(updateTask.getTitle());
		}
		if (updateTask.getImage() != null) {
			existingTask.setImage(updateTask.getImage());
		}
		if (updateTask.getDescription() != null) {
			existingTask.setDescription(updateTask.getDescription());
		}
		if (updateTask.getStatus() != null) {
			existingTask.setStatus(updateTask.getStatus());
		}
		existingTask = taskRepository.save(existingTask);
		return existingTask;
	}

	@Override
	public void deleteTask(Long id) {
		taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
		taskRepository.deleteById(id);
	}

	@Override
	public Task assignedToUser(Long taskId, Long userId) throws Exception {
		Task task = getTaskById(taskId);
		task.setAssignedUserId(userId);
		task.setStatus(TaskStatus.DONE);
		task = taskRepository.save(task);
		return task;
	}

	@Override
	public List<Task> assignedUsersTask(Long userId, TaskStatus status) {
		List<Task> allTasks = taskRepository.findByAssignedUserId(userId);
		return allTasks
			  .stream()
			  .filter(task -> task.getStatus().name().equalsIgnoreCase(status.toString()) || status == null)
			  .toList();
	}

	@Override
	public Task completedTask(Long taskId) throws Exception {
		Task task = getTaskById(taskId);
		task.setStatus(TaskStatus.DONE);
		task = taskRepository.save(task);
		return task;
	}
}
