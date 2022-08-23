package com.koreait.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

// @Entity : JPA가 관리할 객체
//@Entity
@Table(name = "MBR")
public class Member {
	
	// @Id : 데이터베이스 PK와 매핑
	@Id
	private Long id;
	
	@Column(unique = true, length = 10)
	private String name;
	
	@Column(name = "myage")
	private int age;
	
	// 날짜 타입 매핑
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	// 매핑 무시
	@Transient
	private int temp;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	
	
}
