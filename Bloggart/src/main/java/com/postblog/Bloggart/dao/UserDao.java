package com.postblog.Bloggart.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.postblog.Bloggart.entity.UserEntity;

@Repository
public interface UserDao extends JpaRepository<UserEntity, String> {
	UserEntity findByEmail(String email);
}
