package com.koreait.Springtest3.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
	
	@GetMapping("/member")
	public String member(Model model) {
		memberDate(model);
		
		return "member";
	}
	
	private void memberDate(Model model) {
		List<Member> list = new ArrayList<>();
		list.add(new Member(1, 1, "memberA", "01012345670" ));
		list.add(new Member(2, 2, "memberB", "01012345671" ));
		list.add(new Member(3, 1, "memberC", "01012345672" ));
		list.add(new Member(4, 1, "memberD", "01012345673" ));
		list.add(new Member(5, 2, "memberE", "01012345674" ));
		list.add(new Member(6, 2, "memberF", "01012345675" ));
		
		model.addAttribute("member", list);
	}
	
}
