package com.koreait.jpaitem.embed;

import java.time.LocalDateTime;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Member {
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "name", nullable = false)
	private String username;
	
	// 기간
	// @Embedded과 @Embeddable 둘 중에 하나만 넣어도 되나,
	// 둘다 넣는 것을 권장
	@Embedded
	private Period period;
//	private LocalDateTime startDate;
//	private LocalDateTime endDate;
	
	// 주소
	@Embedded
	private Address address;
//	private String city;
//	private String street;
//	private String zipcode;
	
	// 회사주소(추가)
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(column = @Column(name = "WORK_CITY"), name = "city"),
		@AttributeOverride(column = @Column(name = "WORK_STREET"), name = "street"),
		@AttributeOverride(column = @Column(name = "WORK_ZIPCODE"), name = "zipcode")
	})
	private Address workAddress;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Period getPeriod() {
		return period;
	}
	public void setPeriod(Period period) {
		this.period = period;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	
	
	
	
	
	

}
