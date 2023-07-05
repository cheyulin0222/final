package com.example.demo.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "postlike")
public class PostLike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postLikeId;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@JsonIgnoreProperties("postLikes")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "postid")
	private Post post;
	
	@JsonIgnoreProperties({"email", "password", "role", "posts"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberid")
	private Member member;

	@PrePersist
	public void onCreate() {
		if (date == null)
			date = new Date();
	}
	
	public PostLike() {
	}

	public Integer getPostLikeId() {
		return postLikeId;
	}

	public void setPostLikeId(Integer postLikeId) {
		this.postLikeId = postLikeId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	

}
