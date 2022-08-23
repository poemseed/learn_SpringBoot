package com.koreait.item.domain.item;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Item {
	private Long id;
	private String itemName;
	private Integer price;
	private Integer quantity;
	
	// 파라미터 없는 생성자와 파라미터 있는 생성자
	public Item() {}

	public Item(String itemName, Integer price, Integer quantity) {
		super();
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
	}
	
	
}
