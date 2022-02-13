package com.postblog.Bloggart.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.postblog.Bloggart.entity.LikesEntity;
import com.postblog.Bloggart.entity.PostEntity;
import com.postblog.Bloggart.entity.UserEntity;
@Repository
@Transactional
public interface LikesDao extends JpaRepository<LikesEntity, Long> {

//	@Query("select l from likes l where l.postEntity = :postId and l.userEntity = :userId")
//	public LikesEntity findLikes(@Param("postId") Long postId, @Param("userId") Long userId);

//	public LikesEntity findByPostEntityAndUserEntity(Long postId,userId);

	public LikesEntity findByPostEntityAndUserEntity(PostEntity pe, UserEntity ue);

	public List<LikesEntity> findByPostEntity(PostEntity pe);

//	public void remove(LikesEntity le);
	public void deleteById(Long id);

	public void deleteByUserEntity(UserEntity ue);

	public void deleteByPostEntity(PostEntity pe);
}
