package com.postblog.Bloggart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.postblog.Bloggart.dao.CommentDao;
import com.postblog.Bloggart.entity.CommentEntity;
import com.postblog.Bloggart.entity.PostEntity;

@Service
public class CommentService {
	@Autowired
	private CommentDao commentDao;

	public CommentEntity save(CommentEntity comment) {
		return commentDao.save(comment);
	}

	public List<CommentEntity> findAllByPost(PostEntity post) {
		return commentDao.findAllByPost(post);
	}
}
