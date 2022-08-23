package com.koreait.Springtest4.student.domain.student;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Student {
	
	private Long id;
	private String studentName;
	private int age;
	private int subject;
	private String phone;
	private String addr;
	
	public Student() {}

	public Student(String studentName, int age, int subject, String phone, String addr) {
		super();
		this.studentName = studentName;
		this.age = age;
		this.subject = subject;
		this.phone = phone;
		this.addr = addr;
	}
	
	

	
	
}
