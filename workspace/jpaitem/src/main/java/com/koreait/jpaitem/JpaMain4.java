package com.koreait.jpaitem;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.koreait.jpaitem.embed.Address;
import com.koreait.jpaitem.embed.Member;
import com.koreait.jpaitem.embed.Period;


public class JpaMain4 {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			
			Member member = new Member();
			member.setUsername("userA");
			member.setAddress(new Address("서울", "역삼", "123"));
			member.setPeriod(new Period(LocalDateTime.now(), LocalDateTime.now()));
			em.persist(member);
			
			
			tx.commit();
		}catch(Exception e){
			tx.rollback();
		}finally {
			em.close();
			emf.close();
		}
		
		
		
		
		
		
		
	}

}
