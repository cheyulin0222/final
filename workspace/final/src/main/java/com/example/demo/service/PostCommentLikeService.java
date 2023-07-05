package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.PostCommentLike;
import com.example.demo.model.PostCommentLikeRepository;

@Service
public class PostCommentLikeService {
	
	@Autowired
	private PostCommentLikeRepository pDao;
	
	public PostCommentLike insert(PostCommentLike like) {
		return pDao.save(like);
	}
	
	public  void deleteById(Integer id) {
		pDao.deleteById(id);
	}
}
