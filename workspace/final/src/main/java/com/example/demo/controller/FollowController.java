package com.example.demo.controller;

import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Follow;
import com.example.demo.model.Member;
import com.example.demo.model.MemberDTO;
import com.example.demo.service.FollowService;
import com.example.demo.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/follow")
@RequiredArgsConstructor
public class FollowController {

	private final FollowService fService;
	private final MemberService mService;
	
	@GetMapping("/getMyFollowing")
	public List<FollowDTO> findByFollowerId(@NonNull HttpServletRequest request) {
		Integer memberid = mService.findIdByJwt(request);
		
		List<Follow> oldList = fService.findByFollowerid(memberid);
		
		return fService.followListToMemberDTO(oldList);
	}
	
	@PostMapping("/post")
	public List<MemberDTO> add(@NonNull HttpServletRequest request, @RequestBody Follow follow) {
		Member member = mService.findByJwt(request);
		Integer memberid = member.getMemberId();
		
		follow.setFollower(member);
		
		fService.add(follow);
		
		List<Follow> followList = fService.findByFollowerid(memberid);
		List<Integer> idList = fService.extractFollowingID(followList);
		idList.add(memberid);
		
	
		List<Member> oldList = mService.findNotInMemberIdList(idList);
		return mService.memberListToDTO(oldList);
	}
	
	@DeleteMapping("/delete")
	public List<FollowDTO> delete(@NonNull HttpServletRequest request, @RequestParam Integer followId) {
		Integer memberid = mService.findIdByJwt(request);
		
		fService.delete(followId);
		
		List<Follow> oldList = fService.findByFollowerid(memberid);
		
		return fService.followListToMemberDTO(oldList);
	}
}
