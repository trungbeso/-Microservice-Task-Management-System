package com.taskmanagement.controller;

import com.taskmanagement.config.JwtProvider;
import com.taskmanagement.model.User;
import com.taskmanagement.repository.IUserRepository;
import com.taskmanagement.request.LoginRequest;
import com.taskmanagement.response.AuthResponse;
import com.taskmanagement.service.CustomerUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final IUserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final CustomerUserService customerUserService;

	public AuthController(IUserRepository userRepository, PasswordEncoder passwordEncoder, CustomerUserService customerUserService) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.customerUserService = customerUserService;
	}

	@PostMapping("/register")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) {
		String email = user.getEmail();
		String password = user.getPassword();
		String fullName = user.getFullName();
		String roleUser = user.getRole();

		User isEmailExist = userRepository.findByEmail(email);

		if (isEmailExist != null) {
			throw new RuntimeException("Email already exists");
		}

		//create new user
		User newUser = new User();
		newUser.setEmail(email);
		newUser.setPassword(passwordEncoder.encode(password));
		newUser.setFullName(fullName);
		newUser.setRole(roleUser);

		newUser = userRepository.save(newUser);

		Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = JwtProvider.generateToken(authentication);

		AuthResponse response = new AuthResponse();
		response.setMessage("register successful");
		response.setJwt(token);
		response.setStatus(true);

		return new ResponseEntity<>(response, OK);
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> loginHandler(@RequestBody LoginRequest request) {
		String username = request.getEmail();
		String password = request.getPassword();

		Authentication authentication = authenticate(username, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = JwtProvider.generateToken(authentication);

		AuthResponse response = new AuthResponse();
		response.setMessage("logged in successfully");
		response.setJwt(token);
		response.setStatus(true);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	private Authentication authenticate(String username, String password) {
		UserDetails userDetails = customerUserService.loadUserByUsername(username);
		System.out.println("User details: " + userDetails);

		if (userDetails == null) {
			System.out.println("username not found");
			throw new RuntimeException("User not found");
		} else if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			System.out.println("password not match");
			throw new RuntimeException("Invalid password");
		}
		return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
	}

}
