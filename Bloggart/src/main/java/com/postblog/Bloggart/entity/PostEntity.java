package com.postblog.Bloggart.entity;

import java.util.Date;
import java.util.List;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "post")
public class PostEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "post")
	Set<CommentEntity> comments;

	public Set<CommentEntity> getComments() {
		return comments;
	}

	public void setComments(Set<CommentEntity> comments) {
		this.comments = comments;
	}

	private String postHeader;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPostHeader() {
		return postHeader;
	}

	public void setPostHeader(String postHeader) {
		this.postHeader = postHeader;
	}

	public String getPostBody() {
		return postBody;
	}

	public void setPostBody(String postBody) {
		this.postBody = postBody;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	private String postBody;

	@Temporal(TemporalType.DATE)
	private Date postedAtAt = new Date();
//	private List<String> images;

	public Date getPostedAtAt() {
		return postedAtAt;
	}

	public void setPostedAtAt(Date postedAtAt) {
		this.postedAtAt = postedAtAt;
	}

	@ManyToOne
//	@OnDelete(action = OnDeleteAction.CASCADE)
//	@JoinTable(name = "post_user", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"))
	private UserEntity user;

	private Integer likes = 0;

	@Override
	public String toString() {
		return "PostEntity [id=" + id + ", postHeader=" + postHeader + ", postBody=" + postBody + ", postedAtAt="
				+ postedAtAt + ", user=" + user + ", likes=" + likes + "]";
	}

}
