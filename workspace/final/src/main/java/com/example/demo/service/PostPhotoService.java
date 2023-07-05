package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.PostPhoto;
import com.example.demo.model.PostPhotoRepository;

@Service
public class PostPhotoService {
	
	@Autowired
	private PostPhotoRepository pDao;
	
	public PostPhoto insertPhoto(PostPhoto photo) {
		return pDao.save(photo);
	}
	
	public PostPhoto findById(Integer id) {
		Optional<PostPhoto> option = pDao.findById(id);
		
		if (option.isPresent())
			return option.get();
		
		return null;
	}
	
	public List<PostPhoto> findAll() {
		return pDao.findAll();
	}

}
