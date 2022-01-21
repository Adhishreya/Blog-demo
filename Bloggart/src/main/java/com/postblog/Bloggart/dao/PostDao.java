package com.postblog.Bloggart.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.postblog.Bloggart.entity.PostEntity;
import com.postblog.Bloggart.entity.UserEntity;

@Repository
public interface PostDao extends JpaRepository<PostEntity, String>{
//	List<PostEntity> findAllPostsByUserEntities(UserEntity userEntity);
	
	PostEntity save(PostEntity post);

}
