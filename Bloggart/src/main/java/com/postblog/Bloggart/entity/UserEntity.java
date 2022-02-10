package com.postblog.Bloggart.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "user")
@OnDelete(action = OnDeleteAction.CASCADE)
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@Column(name="role", columnDefinition = "default user")
	private String role = "user";

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", role=" + role + ", username=" + username + ", email=" + email + ", password="
				+ password + ", joinedOn=" + joinedOn + ", twitterId=" + twitterId + ", instagramId=" + instagramId
				+ "]";
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getJoinedOn() {
		return joinedOn;
	}

	public void setJoinedOn(Date joinedOn) {
		this.joinedOn = joinedOn;
	}

	public String getTwitterId() {
		return twitterId;
	}

	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}

	public String getInstagramId() {
		return instagramId;
	}

	public void setInstagramId(String instagramId) {
		this.instagramId = instagramId;
	}

	private String username;
	private String email;
	private String password;

//	@OneToMany
//	private List<PostEntity> post;
//
//	@OneToMany
//	private List<CommentEntity> comments;

	@Temporal(TemporalType.DATE)
	private Date joinedOn;

//	private Integer reputation;

	private String twitterId;
//	private String faceBookId;
	private String instagramId;
}
