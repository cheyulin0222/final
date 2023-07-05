package com.example.demo.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FollowDTO {

	private Integer followId;
	private Integer memberId;
	private String firstname;
	private byte[] memberPhoto;
}
