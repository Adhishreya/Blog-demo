package com.postblog.Bloggart.dao;

import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.postblog.Bloggart.entity.UserEntity;

@Repository
@PersistenceContext
@Transactional
public interface UserDao extends JpaRepository<UserEntity, Long> {
	UserEntity findByEmail(String email);

	UserEntity findByUsername(String username);

	@Modifying
	@Query("update UserEntity u  set u.twitterId = :twitter where u.email = :email")
	Integer updateTwitterId(@Param("twitter") String twitter, @Param("email") String email);

	@Modifying
	@Query("update UserEntity u  set u.instagramId = :instagram where u.email = :email")
	Integer updateInstagramId(@Param("instagram") String instagram, @Param("email") String email);

	void deleteById(Long id);
}
