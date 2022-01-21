package com.postblog.Bloggart.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserDto {
//	@NotNull
//	@NotEmpty
//	private String firstName;

	@NotNull
	@NotEmpty
	private String username;

	@NotNull
	@NotEmpty
	private String password;
	private String matchingPassword;

	@NotNull
	@NotEmpty
	private String email;

	// standard getters and setters
}
