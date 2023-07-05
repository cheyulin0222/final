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
@Table(name = "postcommentcommentlike")
public class PostCommentCommentLike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postCommentCommentLikeId;

	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@JsonIgnoreProperties("postCommentCommentLikes")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "postcommentcommentid")
	private PostCommentComment postCommentComment;
	
	@JsonIgnoreProperties("postCommentCommentLikes")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberid")
	private Member member;

	@PrePersist
	public void onCreate() {
		if (date == null)
			date = new Date();
	}

	public PostCommentCommentLike() {
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getPostCommentCommentLikeId() {
		return postCommentCommentLikeId;
	}

	public void setPostCommentCommentLikeId(Integer postCommentCommentLikeId) {
		this.postCommentCommentLikeId = postCommentCommentLikeId;
	}

	public PostCommentComment getPostCommentComment() {
		return postCommentComment;
	}

	public void setPostCommentComment(PostCommentComment postCommentComment) {
		this.postCommentComment = postCommentComment;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}
