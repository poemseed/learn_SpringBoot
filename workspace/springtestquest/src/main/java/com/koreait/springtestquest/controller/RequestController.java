package com.koreait.springtestquest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RequestController {

	@GetMapping("/param/home")
	public String goHome() {
		
		return "/param/home";
	}
	
	@GetMapping("/param/get")
	public String answerCheck(@RequestParam int answer, Model model) {
		String result = "";
		if( answer == 300 ) {
			result = "정답입니다.";
		}else {
			result = "오답입니다.";
		}
		
		model.addAttribute("result", result);
		model.addAttribute("answer", answer);
		
		
		return "/param/getresult";
	}
	
	@PostMapping("/param/post")
	public String formCheck(@RequestParam(defaultValue = "guest") String user_name,
							@RequestParam(defaultValue = "-1") int user_age, Model model) {
		model.addAttribute("username", user_name);
		model.addAttribute("userage", user_age);
		
		return "/param/postresult";
	}
	
	
	
	
	
	
	
}
