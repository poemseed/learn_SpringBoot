package com.koreait.core.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	// 기본 localhost:9091로 요청이 들어오면 home.html을 호출한다.
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	
	
	
	
}
