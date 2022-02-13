package com.postblog.Bloggart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.postblog.Bloggart.dao.PostDao;
import com.postblog.Bloggart.entity.LikesEntity;
import com.postblog.Bloggart.entity.PostEntity;
import com.postblog.Bloggart.entity.UserEntity;

@Service
public class PostService {
	@Autowired
	private PostDao postDaol;

	@Autowired
	private LikesService likesService;

	public void savePost(PostEntity post) {
		postDaol.save(post);
	}

	public List<PostEntity> postList(UserEntity user) {
		List<PostEntity> posts = postDaol.findAllByUser(user);
		return posts;
	}

	public List<PostEntity> postFindAll() {
		List<PostEntity> posts = postDaol.findAll();
		List<PostEntity> edited = new ArrayList<>();
		for (PostEntity p : posts) {
//			System.out.println(p);
			p.setLikes(likesService.findByPostEntity(p));
			edited.add(p);
		}
		return edited;
	}

	public List<Object[]> postFindAllByUserLikes(UserEntity ue) {
		List<PostEntity> posts = postDaol.findAll();
//		List<PostEntity> edited = new ArrayList<>();
		List<Object[]> edited = new ArrayList<>();
		for (PostEntity p : posts) {
//			System.out.println(p);
			Object ob1[] = new Object[2];
			p.setLikes(likesService.findByPostEntity(p));
//			edited.add(p);
			ob1[0] = p;
			LikesEntity le = likesService.findByPostEntityAndUserEntity(p, ue);
			if (le == null) {
				ob1[1] = false;
			} else {
				ob1[1] = true;
			}
			edited.add(ob1);

		}
		return edited;
	}

	public void deletePost(PostEntity post) {
		postDaol.delete(post);
	}

	public PostEntity findById(Long id) {
		PostEntity entityPost = postDaol.findById(id).get();
		return entityPost;
	}

	public void deleteById(Long id) {
		likesService.deleteByPostEntity(findById(id));
		postDaol.deleteById(id);
	}

}
