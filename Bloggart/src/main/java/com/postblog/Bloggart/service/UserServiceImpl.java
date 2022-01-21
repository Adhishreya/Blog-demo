package com.postblog.Bloggart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.postblog.Bloggart.dao.UserDao;
import com.postblog.Bloggart.entity.UserEntity;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public UserEntity save(UserEntity user) {

		return userDao.save(user);
	}

}
