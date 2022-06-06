package com.postblog.Bloggart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.postblog.Bloggart.dao.BookMarkDao;
import com.postblog.Bloggart.dao.PostDao;
import com.postblog.Bloggart.dao.UserDao;
import com.postblog.Bloggart.entity.BookMarkEntity;
import com.postblog.Bloggart.entity.PostEntity;
import com.postblog.Bloggart.entity.UserEntity;

@Service
public class BookMarkService {

	@Autowired
	private PostDao postDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private BookMarkDao bookMarkDao;

	public List<PostEntity> findPostEntitiesByUserEntity(String successName) {
		UserEntity ue = userDao.findByEmail(successName);
		return bookMarkDao.findPostEntityByUserEntity(ue);
	}

	public void removeBookMark(PostEntity postEntity) {
		bookMarkDao.deleteByPostEntity(postEntity);
	}

	public void addBookMark(Long postId, String successName) {
		UserEntity ue = userDao.findByEmail(successName);
		BookMarkEntity bookEntity = bookMarkDao.findByUserEntity(ue);
		if (bookEntity == null) {

		}
//		PostEntity pe = postDao.findById(postId).get();
//		bookMarkDao.save()
	}

}
