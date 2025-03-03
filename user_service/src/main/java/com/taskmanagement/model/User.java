package com.taskmanagement.model;


import jakarta.persistence.*;

@Entity
@Table(name = "users")
public final class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String password;

	private String email;

	private String role;

	private String fullName;

	public User(Long id, String password, String email, String role, String fullName) {
		this.id = id;
		this.password = password;
		this.email = email;
		this.role = role;
		this.fullName = fullName;
	}

	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
