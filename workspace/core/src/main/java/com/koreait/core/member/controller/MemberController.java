package com.koreait.core.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.core.member.dto.Member;
import com.koreait.core.member.dto.MemberForm;
import com.koreait.core.member.service.MemberService;

@Controller
public class MemberController {
	
	// Controller가 Service를 의존한다고 표현한다.
	// Service는 여러 Controller에서 가져다 쓸 수 있다.
	// MemberService memberService = new MemberService();
	
	// 스프링 스럽게 작업하기
	// 1.필드로 주입되는 방식 (좋은 방법이 아니라서 초기에만 사용하고 현재는 권장하지 않음)
//	@Autowired MemberService memberService;
	private final MemberService memberService;
	/*
	 * private	: application 로딩 시점에 조립하기 때문에 중간에 바꿀일이 없다고 보면 된다. 그래서 중간에 바뀔 수 있는 가능성을 아에 닫아놓는다.
	 * final	: 선언된 레퍼런스타입 변수는 반드시 선언과 함께 초기화 되어야 한다. setter주입시에는 의존관계 주입을 받을 필드에 final을 선언할 수가 없다. 
	 * 			객체가 불변하도록 할 수 있다는 점으로, 누군가가 Controller 내부에서 Service객체를 바꿔치기 할수 없다는 점이다.
	 */
	
	
	/*
	 * MemberController가 생성될때 생성자를 호출해준다.
	 * 즉, service까지 생성해서 자동으로 호출해준다.
	 * @Autowired를 선언해주면 MemberController생성하면서 스프링이 memberService와 연결을 해준다.
	 */
	// 2. setter injection 수정자 주입 방식(메서드로 호출되는 시점에 주입되어서 멤버서비스가 없어도 서버기동에 문제가 없다. 그렇기 때문에 권장하지 않음)
//	@Autowired		
//	public void setMemberService(MemberService memberService) {		// 생성자
//		this.memberService = memberService;
//	}
	
	// 3. 생성자를 통한 주입 방식
	// @Autowired : 단일 생성자에 한해 @Autowired는 생략 가능
	@Autowired		// 의존성 주입 어노테이션 
	public MemberController(MemberService memberService) {		// 생성자
		this.memberService = memberService;
	}
	
	@GetMapping(value = "/members/new")
	public String createForm() {
		return "members/createMemberForm";
	}
	
	// 회원가입
	@PostMapping(value = "/members/new")
	public String create(MemberForm form) {
		Member member = new Member();
		member.setName(form.getName());
		
		memberService.join(member);
		
		// 홈 화면으로 돌린다 -> redirect방식
		return "redirect:/";
	}
	
	// 모든 회원 목록
	@GetMapping("/members")
	public String list(Model model) {
		List<Member> members = memberService.findMembers();
		// 메모리에 있는걸 members에 담기
		model.addAttribute("members", members);
		return "members/memberList";
	}
	
	
	
}
