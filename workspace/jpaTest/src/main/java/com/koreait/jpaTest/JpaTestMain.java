package com.koreait.jpaTest;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTestMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		// Transaction	: 데이터베이스의 상태를 변화시키기 위해 수행하는 작업 단위
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			
			Member member = new Member();
			member.setName("member");
			member.setAddress(new Address("서울", "거꾸로 해도 역삼역", "123"));
//			member.setCity("서울");
//			member.setStreet("거꾸로 해도 역삼역");
//			member.setZipcode("123");
			em.persist(member);
			
			for(int i = 0; i < 10; i++ ) {
				Order order = new Order();
				order.setOrderDate(LocalDateTime.now());
				order.setStatus("상품" + i);
				order.changeMember(member);
				em.persist(order);
			}
//			em.flush();
//			em.clear();
			
//			System.out.println("member = " + member.toString());
//			System.out.println(order.toString());
			
			Member findMem = em.find(Member.class, member.getId());
			List<Order> orders = findMem.getOrder();
			
			System.out.println("member = " + member.toString());
			System.out.println("member = " + orders.get(0).getMember().toString());
			for( Order o : orders  ) {
				System.out.println("order =" + o.toString());
			}
			
			
			
			tx.commit();
		}catch(Exception e){
			tx.rollback();
		}finally {
			em.close();
			emf.close();
		}
		
		
		
		
		
		
		
		
		
		
		
	}

}
