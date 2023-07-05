package com.example.demo.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FollowRepository extends JpaRepository<Follow,Integer> {

	@Query("from Follow f where f.follower.memberId = :memberId")
	public List<Follow> findByFollowerid(@Param("memberId") Integer memberId);
}
