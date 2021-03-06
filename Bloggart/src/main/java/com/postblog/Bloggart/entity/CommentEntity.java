package com.postblog.Bloggart.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "comment")
public class CommentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
//	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinTable(name = "comment_post", joinColumns = @JoinColumn(name = "comment_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"))
	private PostEntity post;

	public Long getId() {
		return id;
	}

	private String comment;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

//	@Override
//	public String toString() {
//		return "CommentEntity [id=" + id + ", post=" + post + ", comment=" + comment + ", parentEntity=" + parentEntity
//				+ ", user=" + user + ", commentedAt=" + commentedAt + ", likes=" + likes + "]";
//	}

	public void setId(Long id) {
		this.id = id;
	}

	public PostEntity getPost() {
		return post;
	}

	public void setPost(PostEntity post) {
		this.post = post;
	}

	public CommentEntity getParentEntity() {
		return parentEntity;
	}

	public void setParentEntity(CommentEntity parentEntity) {
		this.parentEntity = parentEntity;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public Date getCommentedAt() {
		return commentedAt;
	}

	public void setCommentedAt(Date commentedAt) {
		this.commentedAt = commentedAt;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	@ManyToOne
	private CommentEntity parentEntity;

	@ManyToOne
	private UserEntity user;

	@Temporal(TemporalType.DATE)
	private Date commentedAt = new Date();;

	private Integer likes;

}
