package com.taskmanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/submission")
	public ResponseEntity<String> home() {
		return new ResponseEntity<>("This is Submission Service", HttpStatus.OK);
	}
}
