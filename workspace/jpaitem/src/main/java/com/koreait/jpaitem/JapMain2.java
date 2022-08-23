package com.koreait.jpaitem;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.koreait.jpaitem.relation.Member;
import com.koreait.jpaitem.relation.Team;

public class JapMain2 {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		// Transaction	: 데이터베이스의 상태를 변화시키기 위해 수행하는 작업 단위
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			Team team = new Team();
			team.setName("TeamA");
			em.persist(team);
			
			Member member = new Member();
			member.setName("member1");
//			member.setTeamId(team.getId());
			member.setTeam(team);	// insert시에 fk값으로 사용한다.
			em.persist(member);
			
			// select
			// 어느팀 소속인지 알고 싶을 때 jpa에 계속 물어봐야 한다.
			/*
			Member findMember = em.find(Member.class, member.getId());
			Long findTeamId = findMember.getTeamId();
			Team findTeam = em.find(Team.class, findTeamId);
			System.out.println("findTeam : " + findTeam.getName());
			*/
			
			// 강제로 db쿼리를 보고 싶을때
			em.flush();
			em.clear();
			
			/*
			// select
			// find시에 1차캐시에서 가지고 와서 select문이 없다.
			Member findMember = em.find(Member.class, member.getId());
			Team findTeam  = findMember.getTeam();
			System.out.println("findTeamName : " + findTeam.getName());
			
			// 양방향 매핑
			Member findSideMember = em.find(Member.class, member.getId());
			List<Member> members = findSideMember.getTeam().getMember();
			
			for( Member m : members ) {
				System.out.println("result = " + m.getName());
			}
			*/
			
			tx.commit();
		}catch(Exception e){
			tx.rollback();
		}finally {
			em.close();
			emf.close();
		}
		
		
		
		
		
		
		
	}

}
