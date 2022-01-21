package com.postblog.Bloggart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.postblog.Bloggart.dao.UserDao;
import com.postblog.Bloggart.dto.UserDto;
import com.postblog.Bloggart.entity.UserEntity;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;

	@Override
	public UserEntity save(UserDto user) {
		UserEntity userEntity = new UserEntity();
		userEntity.setEmail(user.getEmail());
		userEntity.setUsername(user.getUsername());
		try {
			
		}
		return userDao.save(userEntity);
	}

	@Override
	public UserEntity findByEmail(String email) {
		UserEntity userEntity = userDao.findByEmail(email);
		return null;
	}

}
