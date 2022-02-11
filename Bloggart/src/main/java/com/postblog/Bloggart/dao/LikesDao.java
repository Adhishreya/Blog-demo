package com.postblog.Bloggart.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.postblog.Bloggart.entity.LikesEntity;

public interface LikesDao extends JpaRepository<LikesEntity, Long> {

}
