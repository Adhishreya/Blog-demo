package com.postblog.Bloggart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.postblog.Bloggart.dao.LikesDao;
import com.postblog.Bloggart.dao.PostDao;
import com.postblog.Bloggart.dao.UserDao;
import com.postblog.Bloggart.entity.LikesEntity;
import com.postblog.Bloggart.entity.PostEntity;
import com.postblog.Bloggart.entity.UserEntity;

@Service
public class LikesService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private PostDao postDao;

	@Autowired
	private LikesDao likesDao;

	public LikesEntity save(Long postId, String successName) {
		UserEntity ue = userDao.findByEmail(successName);
		PostEntity pe = postDao.findById(postId).get();
		LikesEntity le = new LikesEntity();
		le.setPostEntity(pe);
		le.setUserEntity(ue);
		return likesDao.save(le);
	}

	public void removeLike(Long postId, String successName) {
		UserEntity ue = userDao.findByEmail(successName);
		PostEntity pe = postDao.findById(postId).get();
		likesDao.findByPostEntityAndUserEntity(pe, ue);
	}

	public Integer findByPostEntity(PostEntity pe) {
		System.out.println(likesDao.findByPostEntity(pe).size());
		return likesDao.findByPostEntity(pe).size();
	}
}
