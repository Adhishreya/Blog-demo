package com.postblog.Bloggart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.postblog.Bloggart.dao.PostDao;
import com.postblog.Bloggart.entity.PostEntity;
import com.postblog.Bloggart.entity.UserEntity;

@Service
public class PostService {
	@Autowired
	private PostDao postDaol;
	
	
	public void savePost(PostEntity post) {
		postDaol.save(post);
	}
	
	public void postList(UserEntity user){
		List<PostEntity> posts = postDaol.findAllByUser(user);
		for(PostEntity p : posts) {
			System.out.println(p);
		}
	}
	
}
