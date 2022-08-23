package com.koreait.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/*
 * @Controller	: Controller 클래스에는 @Controller이 필요
 * 				  Controller에서 해당 mapping url을 찾는다 
 */
@Controller
public class HelloController {
	
	// @GetMapping	: get방식의 요청 mapping
	@GetMapping("hello")
	public String hello(Model model) {
		System.out.println("hello");
		model.addAttribute("data", "new springboot!");
		return "hello";
	}
	
	/*
	 * - 컨트롤러에서 리턴 값으로 문자를 반환하면 뷰리졸버(ViewResolver)가 화면을 찾아서 처리한다.
	 * - 스프링 부트는 기본 viewName을 매핑
	 * - resources	: templates/ + {viewName} + .html
	 */

	/*
	 * @RequestParam	: param값을 받아오는데, 필수
	 *  - required		: 파라미터 값 필수 여부, true - 필수(default), false - 필수아님
	 *  - defaultValue	: 파라미터 값이 없ㅂ는 경우 기본으로 들어갈 값
	 */
	@GetMapping("hello-mvc")
	public String helloMvc(@RequestParam(value = "name", required = false, defaultValue = "required test") String name, 
			Model model ) {
		System.out.println("name default = " + name);
		model.addAttribute("name", name);
		return "hello-template";
	}
	
	/*
	 * hello-template.html을 만들어서
	 *  - 서버 테스트시 : 'hello + 파라미터로 넘어온 name'
	 *  - 랜더링싱	: 'hello! empty' 출력
	 */
	
	
	
}
