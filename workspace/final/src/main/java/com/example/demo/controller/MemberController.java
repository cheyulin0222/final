package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Follow;
import com.example.demo.model.Member;
import com.example.demo.model.MemberDTO;
import com.example.demo.service.FollowService;
import com.example.demo.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class MemberController {

	@Autowired
	private MemberService mService;
	
	@Autowired
	private FollowService fService;
	
	
	@PostMapping("/member/getMyAccount")
	public String getMyAccount(@RequestParam("account") String account, @RequestParam("password") String password, HttpSession session) {
		Member m = mService.findByAccountAndPassword(account, password);
		session.setAttribute("loginMember", m);
		
		return "redirect:/";
	}
	
	@GetMapping("/member/getPhoto")
	public ResponseEntity<byte[]> getPhoto(Integer id) {
		Member m = mService.findById(id);
		byte[] photo = m.getMemberPhoto();
		
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(photo, header, HttpStatus.OK);
	}
	
	@GetMapping("/member/getWithoutFollowing")
	public List<MemberDTO> findWithoutFollowing(@NonNull HttpServletRequest request) {
		Integer memberid = mService.findIdByJwt(request);
		
		List<Follow> followList = fService.findByFollowerid(memberid);
		List<Integer> idList = fService.extractFollowingID(followList);
		idList.add(memberid);
		
	
		List<Member> oldList = mService.findNotInMemberIdList(idList);
		return mService.memberListToDTO(oldList);
		
			
		
	}
}
