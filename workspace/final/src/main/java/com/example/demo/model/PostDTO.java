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
public class PostDTO {

	private Integer postId;
	private String location;
	private String firstname;
	private Integer myLikeId;
	private Integer likeAmount;
	private Date postDate;
	private String text;
	private byte[] memberPhoto;
	private List<PostPhotoDTO> postPhotoDTOs;
	
}
