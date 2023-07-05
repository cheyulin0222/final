package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.example.demo.config.JwtService;
import com.example.demo.model.MemberRepository;
import com.example.demo.model.PostCommentComment;
import com.example.demo.model.PostCommentCommentDTO;
import com.example.demo.model.PostCommentCommentLike;
import com.example.demo.model.PostCommentCommentRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class PostCommentCommentService {

	@Autowired
	private PostCommentCommentRepository pDao;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private MemberRepository mDao;
	
	public PostCommentComment add(PostCommentComment postCommentComment, @NonNull HttpServletRequest request) {
		
		final String authHeader = request.getHeader("Authorization");
		final String jwt = authHeader.substring(7);
		final String useremail = jwtService.extractUsername(jwt);
		var member = mDao.findByEmail(useremail)
				.orElseThrow();
		postCommentComment.setMember(member);
		
		return pDao.save(postCommentComment);
	}
	
	public List<PostCommentCommentDTO> findPostCommentCommentsByPostCommentId(Integer postCommentId, @NonNull HttpServletRequest request) {
		final String authHeader = request.getHeader("Authorization");
		final String jwt = authHeader.substring(7);
		final String useremail = jwtService.extractUsername(jwt);
		var member = mDao.findByEmail(useremail)
				.orElseThrow();
		Integer memberid = member.getMemberId();
		
		
		List<PostCommentCommentDTO> newList = new ArrayList<PostCommentCommentDTO>();
		
		List<PostCommentComment> oldList = pDao.findPostCommentCommentListByPostId(postCommentId);
		for (PostCommentComment comment: oldList) {
			for (PostCommentCommentLike like : comment.getPostCommentCommentLikes()) {
				if (memberid == like.getMember().getMemberId()) {
					comment.setMyLikeId(like.getPostCommentCommentLikeId());
				}
			}
			PostCommentCommentDTO newComment = PostCommentCommentDTO.builder()
				.postCommentCommentId(comment.getPostCommentCommentId())
				.text(comment.getText())
				.date(comment.getDate())
				.myLikeId(comment.getMyLikeId())
				.firstname(comment.getMember().getFirstname())
				.memberPhoto(comment.getMember().getMemberPhoto())
				.build();
			newList.add(newComment);
		}
		return newList;
	}
}
