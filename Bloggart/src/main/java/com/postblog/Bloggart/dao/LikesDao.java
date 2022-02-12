package com.postblog.Bloggart.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.postblog.Bloggart.entity.LikesEntity;
import com.postblog.Bloggart.entity.PostEntity;
import com.postblog.Bloggart.entity.UserEntity;

public interface LikesDao extends JpaRepository<LikesEntity, Long> {

//	@Query("select l from likes l where l.postEntity = :postId and l.userEntity = :userId")
//	public LikesEntity findLikes(@Param("postId") Long postId, @Param("userId") Long userId);

//	public LikesEntity findByPostEntityAndUserEntity(Long postId,userId);

	public LikesEntity findByPostEntityAndUserEntity(PostEntity pe, UserEntity ue);

	public List<LikesEntity> findByPostEntity(PostEntity pe);

}
