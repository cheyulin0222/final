package com.example.demo.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "messages")
public class Messages {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String text;
	
	@DateTimeFormat(pattern = "yyyy/MMdd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date added;
	
	@PrePersist
	public void onCreate() {
		if (added == null)
			added = new Date();
	}
	
	public Messages() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getAdded() {
		return added;
	}

	public void setAdded(Date added) {
		this.added = added;
	}
	
	
}
