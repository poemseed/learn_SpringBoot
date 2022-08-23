package com.koreait.core.member.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.koreait.core.member.dto.Member;

//@Repository		// 스프링 컨테이너가 레파지토리를 관리할 수 있게 하는 어노테이션
public class MemoryMemberRepository implements MemberRepository{	// dao와 같은 역할
	
	// 메모리 사용 - static (고정메모리 영역에 올려놓음)
	private static Map<Integer, Member> store = new HashMap<Integer, Member>();		// db처럼 사용(db가 아직 정해지지 않았을 때)
	private static int sequence = 0;	// id
	

	@Override
	public Member save(Member member) {
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}

	@Override
	public List<Member> findAll() {

		return new ArrayList<Member>(store.values());
	}
	
	
	
	
	
}
