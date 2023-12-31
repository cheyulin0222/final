package com.example.demo.auth;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthenticationService service;
	
	@PostMapping("/register") 
	public ResponseEntity<AuthenticationResponse> register(
			@RequestParam("firstname") String firstname,
			@RequestParam("lastname") String lastname,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("file") MultipartFile file
	) {
		
		try {
			return ResponseEntity.ok(service.register(firstname, lastname, email, password, file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@PostMapping("/authenticate") 
	public ResponseEntity<AuthenticationResponse> authenticate(
			@RequestBody AuthenticationRequest request
	) {
		return ResponseEntity.ok(service.authenticate(request));
	}
}
