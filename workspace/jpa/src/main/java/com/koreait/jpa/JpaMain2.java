package com.koreait.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain2 {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		// Transaction	: 데이터베이스의 상태를 변화시키기 위해 수행하는 작업 단위
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			
			// 비영속 상태
			Member member = new Member();
			member.setId(100L);
			member.setName("JPA1");
			
			// 여기서부터 영속 상태
			// EntityManager 안에 있는 영속 컨텍스트에 관리가 된다는 뜻
			// 아직 DB에는 저장이 안된 상태
			// 만약 em.persist(member); 여기에서 DB쿼리가 실행된다면,
			// DB쿼리가 실행이 되어야 한다.
			System.out.println("------before-----");
			em.persist(member);
			System.out.println("------after-----");
			
			// 쿼리 실행 시점
			tx.commit();
		}catch(Exception e){
			tx.rollback();
		}finally {
			em.close();
			emf.close();
		}
		
		
		
		
	}

}
