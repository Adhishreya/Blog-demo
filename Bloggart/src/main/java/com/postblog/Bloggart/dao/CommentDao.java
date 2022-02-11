package com.postblog.Bloggart.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.postblog.Bloggart.entity.CommentEntity;
import com.postblog.Bloggart.entity.PostEntity;

@Repository
public interface CommentDao extends JpaRepository<CommentEntity, Long> {
	CommentEntity save(CommentEntity comment);

	List<CommentEntity> findAllByPost(PostEntity post);
}
