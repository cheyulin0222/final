package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.controller.MessagesRepository;
import com.example.demo.model.Messages;

@Service
public class MessagesService {
	
	@Autowired
	private MessagesRepository mDao;
	
	public void insertMessage(Messages msg) {
		mDao.save(msg);
	}
	
	public Page<Messages> findByPage(Integer pageNumber){
		PageRequest pgb = PageRequest.of(pageNumber-1, 3, Sort.Direction.DESC, "added");
		Page<Messages> page = mDao.findAll(pgb);
		return page;
	}
	
}
