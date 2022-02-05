package com.postblog.Bloggart.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.postblog.Bloggart.dao.UserDao;
import com.postblog.Bloggart.dto.UserDto;
import com.postblog.Bloggart.entity.UserEntity;
import com.postblog.Bloggart.exceptions.EmailAlreadyExistsException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserEntity save(UserDto user) throws EmailAlreadyExistsException {
		UserEntity userEntity = new UserEntity();
		userEntity.setEmail(user.getEmail());
		userEntity.setUsername(user.getUsername());
		userEntity.setPassword(passwordEncoder.encode(user.getPassword()));

		Date date = new Date();

		userEntity.setJoinedOn(date);

		if (findByEmail(userEntity.getEmail()) == null) {
			return userDao.save(userEntity);
		} else {
			throw new EmailAlreadyExistsException();
		}
	}

	@Override
	public UserEntity findByEmail(String email) {
		UserEntity userEntity = userDao.findByEmail(email);
		return userEntity;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity user = userDao.findByEmail(username);
		if (user == null)
			throw new UsernameNotFoundException("Email not found!");
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), null);
	}
	
	@Override
	public List<GrantedAuthority> getGrantedAuthorities(UserEntity user){
		List<GrantedAuthority> granted = new ArrayList();
		granted.addAll(user.getRole()=="user");
	}
}
