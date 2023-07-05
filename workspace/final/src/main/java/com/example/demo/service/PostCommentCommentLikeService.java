package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.PostCommentCommentLike;
import com.example.demo.model.PostCommentCommentLikeRepository;

@Service
public class PostCommentCommentLikeService {
	
	@Autowired
	private PostCommentCommentLikeRepository pDao;
	
	public PostCommentCommentLike insert(PostCommentCommentLike like) {
		return pDao.save(like);
	}
	
	public void deleteById(Integer postCommentCommentLikeId) {
		pDao.deleteById(postCommentCommentLikeId);
	}
}
