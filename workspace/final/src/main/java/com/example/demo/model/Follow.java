package com.example.demo.model;



import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "follow")
public class Follow {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer followId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "followerid")
	private Member follower;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "followingid")
	private Member following;
	
}
