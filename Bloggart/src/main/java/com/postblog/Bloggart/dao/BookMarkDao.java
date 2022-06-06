package com.postblog.Bloggart.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.postblog.Bloggart.entity.BookMarkEntity;
import com.postblog.Bloggart.entity.PostEntity;
import com.postblog.Bloggart.entity.UserEntity;

@Transactional
public interface BookMarkDao extends JpaRepository<BookMarkEntity, Integer>{
	
	List<PostEntity> findPostEntityByUserEntity(UserEntity userEntity);
	
	void deleteByPostEntity(PostEntity postEntity);
	
	BookMarkEntity findByUserEntity(UserEntity userEntity);
	

}
