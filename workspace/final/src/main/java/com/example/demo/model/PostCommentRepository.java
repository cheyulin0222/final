package com.example.demo.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostCommentRepository extends JpaRepository<PostComment, Integer> {

//	@Query ("from PostComment where postid = :postId ")
//	public List<PostComment> findPostCmmentListByPostId(@Param("postId") Integer postId);
	
	@Query ("from PostComment c where c.post.postId = :postId ")
	public List<PostComment> findPostCmmentListByPostId(@Param("postId") Integer postId);
		
}
