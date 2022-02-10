package com.postblog.Bloggart.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.postblog.Bloggart.dto.UserDto;
import com.postblog.Bloggart.entity.UserEntity;
import com.postblog.Bloggart.exceptions.EmailAlreadyExistsException;

public interface UserService extends UserDetailsService {
	UserEntity save(UserDto user) throws EmailAlreadyExistsException;

	UserEntity findByEmail(String email);

	UserEntity update(UserEntity user);

	Integer updateTwitterId(String twitter, String email);

	Integer updateInstagramId(String instagram, String email);
	
	void deleteById(Long id);

}
