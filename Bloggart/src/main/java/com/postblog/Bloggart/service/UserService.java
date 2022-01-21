package com.postblog.Bloggart.service;

import com.postblog.Bloggart.dto.UserDto;
import com.postblog.Bloggart.entity.UserEntity;

public interface UserService {
	UserEntity save(UserDto user);

	UserEntity findByEmail(String email);

}
