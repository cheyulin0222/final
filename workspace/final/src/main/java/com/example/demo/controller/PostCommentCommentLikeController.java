package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Member;
import com.example.demo.model.PostCommentCommentLike;
import com.example.demo.model.PostCommentCommentLikeDTO;
import com.example.demo.service.MemberService;
import com.example.demo.service.PostCommentCommentLikeService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class PostCommentCommentLikeController {

	@Autowired
	private PostCommentCommentLikeService pService;
	@Autowired
	private MemberService mService;
	
	
	@PostMapping("/postCommentCommentLike/post")
	public PostCommentCommentLikeDTO insert(@RequestBody PostCommentCommentLike like, @NonNull HttpServletRequest request) {
		Member member = mService.findByJwt(request);
		
		like.setMember(member);

		PostCommentCommentLike newLike = pService.insert(like);

		return PostCommentCommentLikeDTO.builder().postCommentCommentLikeId(newLike.getPostCommentCommentLikeId()).build();

	}
	
	@DeleteMapping("/postCommentCommentLike/delete")
	public void delete(@RequestParam Integer postCommentCommentLikeId) {
		pService.deleteById(postCommentCommentLikeId);
	}
}
