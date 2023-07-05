package com.example.demo.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostCommentCommentDTO {

	private Integer postCommentCommentId;
	private String text;
	private Date date;
	private Integer myLikeId;
	private String firstname;
	private byte[] memberPhoto;
}
