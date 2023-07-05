package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "postphoto")
public class PostPhoto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postPhotoId;
	
	@JsonIgnoreProperties("postPhotos")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "postid")
	private Post post;
	
	private byte[] postPhotoFile;
	
	public PostPhoto() {
	}

	public Integer getPostPhotoId() {
		return postPhotoId;
	}

	public void setPostPhotoId(Integer postPhotoId) {
		this.postPhotoId = postPhotoId;
	}

	
	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public byte[] getPostPhotoFile() {
		return postPhotoFile;
	}

	public void setPostPhotoFile(byte[] postPhotoFile) {
		this.postPhotoFile = postPhotoFile;
	}
	
	

}
