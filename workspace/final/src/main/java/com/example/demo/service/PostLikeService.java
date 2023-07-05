package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.PostLike;
import com.example.demo.model.PostLikeRepository;

@Service
public class PostLikeService {
	
	@Autowired
	private PostLikeRepository pDao;
	
	public PostLike insert(PostLike like) {
		return pDao.save(like);
	}
	
	public void deleteById(Integer id) {
		pDao.deleteById(id);
	}
}
