package com.taskmanagement.service;

import com.taskmanagement.config.JwtProvider;
import com.taskmanagement.model.User;
import com.taskmanagement.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

	private final IUserRepository userRepository;

	public UserService(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User getUserProfile(String jwt) {
		String email = JwtProvider.getEmailFromJwtToken(jwt);
		return userRepository.findByEmail(email);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
}
