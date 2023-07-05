package com.example.demo.model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostCommentDTO {

	private Integer postCommentId;
	private String text;
	private Date date;
	private Integer myLikeId;
	private List<PostCommentCommentDTO> postCommentComments;
	private Integer replyAmount;
	private String firstname;
	private byte[] memberPhoto;
	
	
}
