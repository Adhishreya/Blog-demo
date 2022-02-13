package com.postblog.Bloggart.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.postblog.Bloggart.dto.UserDto;
import com.postblog.Bloggart.entity.UserEntity;
import com.postblog.Bloggart.exceptions.EmailAlreadyExistsException;

public interface UserService extends UserDetailsService {
	UserEntity save(UserDto user) throws EmailAlreadyExistsException;

	UserEntity findByEmail(String email);
	
	UserEntity findById(Long id);

	UserEntity update(UserEntity user);

	Integer updateTwitterId(String twitter, String email);

	Integer updateInstagramId(String instagram, String email);

	Integer updateBio(String bio, String email);

	Integer updateImage(String image, String email);

	void deleteById(Long id);

}
