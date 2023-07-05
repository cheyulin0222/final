package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.example.demo.config.JwtService;
import com.example.demo.model.Member;
import com.example.demo.model.MemberDTO;
import com.example.demo.model.MemberRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository mDao;
	
	@Autowired
	private JwtService jwtService;
	
	public Member findById(Integer id) {
		Optional<Member> option = mDao.findById(id);
		
		if (option.isPresent())
			return option.get();
		
		return null;
	}
	
	public Member insertMember(Member m) {
		return mDao.save(m);
	}
	
	public Member findByAccountAndPassword(String memberAccount, String password) {
		return mDao.findByAccountAndPassword(memberAccount, password);
	}
	
	public Integer findIdByJwt(@NonNull HttpServletRequest request) {
		String jwt = request.getHeader("Authorization").substring(7);
		String userEmail = jwtService.extractUsername(jwt);
		
		Optional<Member> option = mDao.findByEmail(userEmail);
		if (option.isPresent()) {
			Member member = option.get();
			return member.getMemberId();
		}
		
		return null;
		
	}
	
	public Member findByJwt(@NonNull HttpServletRequest request) {
		String jwt = request.getHeader("Authorization").substring(7);
		String userEmail = jwtService.extractUsername(jwt);
		
		Optional<Member> option = mDao.findByEmail(userEmail);
		if (option.isPresent()) {
			return option.get();
		}
		
		return null;
		
	}
	
	public List<Member> findNotInMemberIdList(List<Integer> memberIdList) {
		return mDao.findNotInMemberId(memberIdList);
	}
	
	public List<MemberDTO> memberListToDTO(List<Member> memberList) {
		List<MemberDTO> newList = new ArrayList<MemberDTO>();
		for (Member member :memberList) {
			MemberDTO build = MemberDTO.builder()
				.memberId(member.getMemberId())
				.firstname(member.getFirstname())
				.memberPhoto(member.getMemberPhoto())
				.build();
			newList.add(build);
		}
		return newList;
	}
	
	
	
	
	
}
