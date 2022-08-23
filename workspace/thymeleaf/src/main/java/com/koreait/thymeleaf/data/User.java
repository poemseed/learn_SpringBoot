package com.koreait.thymeleaf.data;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class User {

	private String username;
	private int age;
	
	public User(String username, int age) {
		this.username = username;
		this.age = age;
	}
	
}
