package com.postblog.Bloggart.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.postblog.Bloggart.validators.ValidEmail;

public class UserDto {
//	@NotNull
//	@NotEmpty
//	private String firstName;

	@NotNull
	@NotEmpty(message="Username cannot be empty")
	private String username;

	@NotNull
	@NotEmpty(message="Password cannot be empty")
	@Size(min = 8,max=20,message="Password must be atleast {min} and less than {max} characters")
	private String password;
	private String matchingPassword;

	@ValidEmail
	@NotNull
	@NotEmpty(message="Email cannot be empty")
	private String email;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// standard getters and setters
}
