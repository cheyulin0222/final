package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.PostPhoto;
import com.example.demo.service.PostPhotoService;

@Controller
public class PostPhotoController {
	
	@Autowired
	private PostPhotoService pService;
	
	@GetMapping("/postPhoto/getPhoto")
	public ResponseEntity<byte[]> getImage(Integer id) {
		PostPhoto photo = pService.findById(id);
		byte[] photoFile = photo.getPostPhotoFile();
		
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.IMAGE_JPEG);
		
		return new ResponseEntity<byte[]>(photoFile, header, HttpStatus.OK);
	}
}
