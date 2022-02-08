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

	public List<PostEntity> postList(UserEntity user) {
		List<PostEntity> posts = postDaol.findAllByUser(user);
		return posts;
	}

	public List<PostEntity> postFindAll() {
		List<PostEntity> posts = postDaol.findAll();
		for (PostEntity p : posts) {
			System.out.println(p);
		}
		return posts;
	}

	public void deletePost(PostEntity post) {
		postDaol.delete(post);
	}

	public PostEntity findById(Long id) {
		List<PostEntity> postEnt = (List<PostEntity>) postDaol.findById(id).get();
		return postEnt.get(0);
	}

	public void deleteById(Long id) {
		postDaol.deleteById(id);
	}

}
