package com.koreait.core.member.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

// @Entity	: jpa가 관리하는 class
@Entity
public class Member {
	
	// @Id	: 기본키 매핑
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySequenceGenerator")				// 시퀀스를 쓰고 있다고 알려줌
	@SequenceGenerator(name = "mySequenceGenerator", sequenceName = "member_seq", allocationSize = 1)	// 시퀀스를 쓰고 있다고 알려줌
	private int id;
	//@Column(name = "username")
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
