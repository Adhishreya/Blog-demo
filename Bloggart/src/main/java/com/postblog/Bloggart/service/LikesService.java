package com.postblog.Bloggart.service;

import java.util.ArrayList;
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
		LikesEntity le = likesDao.findByPostEntityAndUserEntity(pe, ue);
		if (le != null) {
			likesDao.deleteById(le.getId());
		}
	}

	public Integer findByPostEntity(PostEntity pe) {
		System.out.println(likesDao.findByPostEntity(pe).size());
		return likesDao.findByPostEntity(pe).size();
	}

	public List<Object[]> findLikedBy(PostEntity pe) {
		List<Object[]> likedByList = new ArrayList<>();

		List<LikesEntity> likes = likesDao.findByPostEntity(pe);
		for (LikesEntity l : likes) {
			Object ob1[] = new Object[2];
			ob1[0] = l.getUserEntity().getUsername();
			ob1[1] = l.getPostedAtAt().toString();
//			System.out.println(ob1[0]+"        "+ob1[1]);
			likedByList.add(ob1);
		}
		return likedByList;
	}

//	public List<LikesEntity> findByPostEntityAndUserEntity(PostEntity pe, UserEntity ue){
//		return likesDao.findByPostEntityAndUserEntity(pe, ue);
//	}

	public LikesEntity findByPostEntityAndUserEntity(PostEntity pe, UserEntity ue) {
		return likesDao.findByPostEntityAndUserEntity(pe, ue);
	}

	public void deleteByUserEntity(UserEntity ue) {
		likesDao.deleteByUserEntity(ue);
	}

	public void deleteByPostEntity(PostEntity pe) {
		likesDao.deleteByPostEntity(pe);
	}
}
