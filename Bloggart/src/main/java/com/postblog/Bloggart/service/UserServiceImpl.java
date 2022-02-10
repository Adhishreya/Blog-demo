package com.postblog.Bloggart.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), enabled,
				accountNonExpired, credentialsNonExpired, accountNonLocked, getGrantedAuthorities(user.getRole()));
	}

	private static List<GrantedAuthority> getGrantedAuthorities(String role) {
		List<GrantedAuthority> granted = new ArrayList<>();
//		granted.addAll(user.getRole()=="user");
		granted.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		return granted;
	}

	@Override
	public UserEntity update(UserEntity user) {
		UserEntity entity = userDao.save(user);
		return null;
	}

	@Override
	public Integer updateTwitterId(String twitter, String email) {
		return userDao.updateTwitterId(twitter, email);
	}

	@Override
	public Integer updateInstagramId(String instagram, String email) {
		return userDao.updateInstagramId(instagram, email);
	}

//	@Override
//	public void deleteById(Long id) {
//		userDao.deleteById(id);
//	}
}
