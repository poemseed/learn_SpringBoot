package com.koreait.core.member.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koreait.core.member.dto.Member;
import com.koreait.core.member.repository.MemberRepository;
import com.koreait.core.member.repository.MemoryMemberRepository;

/*
 * MemberService는 순수한 java class(POJO)이다. 그래서 스프링이 알 수 없다.
 * 스프링이 찾을 수 있도록 적절한 어노테이션을 선언해 주어야 한다. 
 */
@Service		// 스프링에게 관리해야한다고 알려주는 어노테이션 서비스를 붙여줘야한다.
@Transactional	// JPA를 사용
public class MemberService {
	// MemberRepository memberRepository = new MemoryMemberRepository();	
	// 멤버서비스가 멤버레파지토리를 의존한다.
	MemberRepository memberRepository;
	
	//생성자 주입(의존성 주입)
	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	// 회원가입
	public int join(Member member) {
		memberRepository.save(member);
		return member.getId();
	}
	
	// 전체회원 조회
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
	
	
	
}
