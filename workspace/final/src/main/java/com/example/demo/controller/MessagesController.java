package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Messages;
import com.example.demo.service.MessagesService;

@Controller
public class MessagesController {

	@Autowired
	private MessagesService mService;
	
	@ResponseBody
	@PostMapping("/messages/ajax/post")
	public Page<Messages> addMsgApi(@RequestBody Messages msg){
		
		Messages newMsg = new Messages();
		newMsg.setText(msg.getText());
		
		mService.insertMessage(newMsg);
		
		Page<Messages> page = mService.findByPage(1);
		
		return page;
	}
	
	@GetMapping("/messages/ajaxdemo")
    public String ajaxdemo() {
    	
    	return "messages/ajax-message";
    }
	
}
