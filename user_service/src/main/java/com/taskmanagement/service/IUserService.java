package com.taskmanagement.service;

import com.taskmanagement.model.User;

import java.util.List;

public interface IUserService {
	User getUserProfile(String jwt);

	List<User> getAllUsers();
}
