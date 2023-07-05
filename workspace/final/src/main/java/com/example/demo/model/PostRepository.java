package com.example.demo.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Integer> {

	@Query(value = "select * from Post where memberid in :memberidList", nativeQuery = true)
	public List<Post> findByMemberList(@Param("memberidList") List<Integer> memberidList);

	@Query("from Post p where p.member.memberId = :memberId")
	public List<Post> findAllByMemberId(@Param("memberId") Integer memberId);
}
