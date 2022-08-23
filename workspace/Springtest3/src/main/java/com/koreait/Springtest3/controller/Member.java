package com.koreait.Springtest3.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {
	private int num;
	private int genderNum;
	private String memberName;
	private String phoneNum;
	
	public Member(int num, int genderNum, String memberName, String phoneNum) {
		super();
		this.num = num;
		this.genderNum = genderNum;
		this.memberName = memberName;
		this.phoneNum = phoneNum;
	}
	
	
	
}
