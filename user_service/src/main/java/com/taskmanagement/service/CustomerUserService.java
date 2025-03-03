package com.taskmanagement.service;

import com.taskmanagement.model.User;
import com.taskmanagement.repository.IUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerUserService implements UserDetailsService {

	private final IUserRepository userRepository;

	public CustomerUserService(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("user not found with username: " + username);
		}
		List<GrantedAuthority> auth = new ArrayList<>();

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), auth);
	}
}
