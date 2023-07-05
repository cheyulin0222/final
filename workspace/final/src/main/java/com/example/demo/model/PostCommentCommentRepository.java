package com.example.demo.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostCommentCommentRepository extends JpaRepository<PostCommentComment, Integer> {

	@Query ("from PostCommentComment c where c.postComment.postCommentId = :postCommentId ")
	public List<PostCommentComment> findPostCommentCommentListByPostId(@Param("postCommentId") Integer postCommentId);
}
