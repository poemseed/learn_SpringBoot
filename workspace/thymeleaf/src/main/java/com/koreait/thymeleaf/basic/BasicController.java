package com.koreait.thymeleaf.basic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koreait.thymeleaf.data.User;

@Controller
@RequestMapping("/basic")	// 매번 반복되는 경로를 이렇게 처리할수 있다
public class BasicController {

	@GetMapping("text-basic")
	public String textBasic(Model model) {
		
		model.addAttribute("data", "SpringBoot!");
		return "basic/text-basic";
	}
	
	@GetMapping("text-unescaped")
	public String textUnescaped(Model model) {
		
		model.addAttribute("data", "<b>SpringBoot!</b>");
		return "basic/text-unescaped";
	}
	
	@GetMapping("variable")
	public String variable(Model model) {
		
		User userA = new User("userA", 20);
		User userB = new User("userB", 10);
		
		// 리스트에 담기(객체가 두건이상 일때 사용)
		List<User> list = new ArrayList<>();
		list.add(userA);
		list.add(userB);
		
		// 해쉬맵에 담기(객체가 두건이상 일때 사용)
		Map<String, User> map = new HashMap<>();
		map.put("userA", userA);
		map.put("userB", userB);
		
		
		model.addAttribute("user", userA);	// 객체 통으로 담아 보내주는 것
		model.addAttribute("users", list);	// ArrayList에 담아서 보내주는것
		model.addAttribute("userMap", map);	// HashMap에 담아서 보내주는것
		
		
		return "basic/variable";
	}
	
	@GetMapping("basic-objects")
	public String basicObjects(HttpSession session) {	// 세션값 받아오기
		session.setAttribute("sessionData", "UserID");	
		
		return "basic/basic-objects";
	}
	
	// 시간 받아오기
	@GetMapping("/date")
	public String date(Model model) {
		model.addAttribute("localDateTime", LocalDateTime.now());
		return "basic/date";
	}
	
	@GetMapping("/link")
	public String link(Model model) {
		model.addAttribute("param1", "data1");
		model.addAttribute("param2", "data2");
		return "basic/link";
	}
	
	@GetMapping("/literal")
	public String literal(Model model) {
		model.addAttribute("data", "Spring");
		return "basic/literal";
	}
	
	// 연산자
	@GetMapping("/operation")
	public String operation(Model model) {
		model.addAttribute("nulldata", null);
		model.addAttribute("data", "spring");
		return "basic/operation";
	}
	
	// 속성값 설정
	@GetMapping("/attribute")
	public String attribute() {
		return "basic/attribute";
	}
	
	//반복문
	@GetMapping("/each")
	public String each(Model model) {
		// 하나의 역할만 하도록 따로 분리해서 모듈화
//		List<User> list = new ArrayList<>();
//		list.add(new User("userA", 10));
//		list.add(new User("userB", 20));
//		list.add(new User("userC", 30));
//		model.addAttribute("users", list);
		addUsers(model);
		return "basic/each";
	}
	
	private void addUsers(Model model) {
		List<User> list = new ArrayList<>();
		list.add(new User("userA", 10));
		list.add(new User("userB", 20));
		list.add(new User("userC", 30));
		model.addAttribute("users", list);
	}
	
	// 조건문
	@GetMapping("/condition")
	public String condition(Model model) {
		addUsers(model);
		return "basic/condition";
	}
	
	// 주석
	@GetMapping("/comments")
	public String comments(Model model) {
		model.addAttribute("data", "spring");
		return "basic/comments";
	}
	
	// 블록
	@GetMapping("/block")
	public String block(Model model) {
		addUsers(model);
		return "basic/block";
	}
	
	// 자바스크립트 인라인
	@GetMapping("/javascript")
	public String javascript(Model model) {
		model.addAttribute("user", new User("userA", 20));
		addUsers(model);
		return "basic/javascript";
	}
	
	
	
	
	
	
}
