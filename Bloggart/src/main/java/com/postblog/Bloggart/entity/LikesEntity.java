package com.postblog.Bloggart.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "likes")
public class LikesEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	private Date postedAtAt = new Date();

//	@ManyToOne
	@OneToOne
	private PostEntity postEntity;

	@Override
	public String toString() {
		return "LikesEntity [id=" + id + ", postedAtAt=" + postedAtAt + ", postEntity=" + postEntity + ", userEntity="
				+ userEntity + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getPostedAtAt() {
		return postedAtAt;
	}

	public void setPostedAtAt(Date postedAtAt) {
		this.postedAtAt = postedAtAt;
	}

	public PostEntity getPostEntity() {
		return postEntity;
	}

	public void setPostEntity(PostEntity postEntity) {
		this.postEntity = postEntity;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

//	@ManyToOne
	@OneToOne
	private UserEntity userEntity;

}
