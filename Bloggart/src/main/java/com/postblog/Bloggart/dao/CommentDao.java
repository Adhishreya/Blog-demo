package com.postblog.Bloggart.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.postblog.Bloggart.entity.CommentEntity;

@Repository
public interface CommentDao extends JpaRepository<CommentEntity, Long> {
	CommentEntity save(CommentEntity comment);
}
