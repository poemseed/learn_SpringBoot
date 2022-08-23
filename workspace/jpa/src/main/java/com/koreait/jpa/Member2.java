package com.koreait.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "MEMBER2_SEQ_GENERATOR", sequenceName = "MEMBER_SEQ", initialValue = 1, allocationSize = 1)
public class Member2 {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER2_SEQ_GENERATOR")
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String username;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
