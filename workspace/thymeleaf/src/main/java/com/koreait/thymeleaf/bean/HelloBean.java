package com.koreait.thymeleaf.bean;

import org.springframework.stereotype.Component;

@Component("helloBean")	//helloBean이름으로 스프링 컨테이너에 싱글톤으로 빈으로 등록됌
public class HelloBean {

	public String hello(String data) {
		return "Bean" + data;
	}
}
