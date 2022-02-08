package com.postblog.Bloggart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.postblog.Bloggart.dao.CommentDao;
import com.postblog.Bloggart.entity.CommentEntity;

@Service
public class CommentService {
	@Autowired
	private CommentDao commentDao;

	public CommentEntity save(CommentEntity comment) {
		return commentDao.save(comment);
	}
}
