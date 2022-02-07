package com.postblog.Bloggart.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.postblog.Bloggart.entity.PostEntity;
import com.postblog.Bloggart.entity.UserEntity;

@Repository
public interface PostDao extends JpaRepository<PostEntity, Long>{
	List<PostEntity> findAllByUser(UserEntity userEntity);
	
	List<PostEntity> findAll();
	
	PostEntity save(PostEntity post);
	
	void delete(PostEntity post);
	
	void deleteById(Long id);
	
//	List<PostEntity> findAllPostsByEmailId
}
