package com.koreait.core.member.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.koreait.core.member.dto.Member;

@Repository
public class JpaMemberRepository implements MemberRepository{
	
	// JPA는 EntityManager라는 것으로 모든지 동작을 한다.
	// JPA를 사용하려면 EntityManager를 주입받아야한다.
	private final EntityManager em;
	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Member save(Member member) {
		em.persist(member);		// insert구문 한줄로 끝
		
		return member;
	}

	@Override
	public List<Member> findAll() {
		// select m from Member m : JPQL이라는 쿼리. Member라는 Entity를 조회해 select할때 Member라는 객체 자체를 select한다.
		return em.createQuery("select m from Member m", Member.class).getResultList();
	}

}
