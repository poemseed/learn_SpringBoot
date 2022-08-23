package com.koreait.item.domain.web.basic;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.item.domain.item.Item;
import com.koreait.item.domain.item.ItemRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
/*
 * @RequiredArgsConstructor	: final이 붙은 멤버변수만 사용해서 생성자를 자동으로 만들어준다.
 */
public class BasicItemController {
	
	private final ItemRepository itemRepository;
	
	//@Autowired
	/*
	 * 생성자가 딱 1개만 있으면 스프링이 해당 생성자에게 @Autowired로 의존관계를 주입해준다.
	 */
//	public BasicItemController(ItemRepository itemRepository) {
//		this.itemRepository = itemRepository;
//	}
	
	@GetMapping
	public String items(Model model) {
		List<Item> items = itemRepository.findAll();
		model.addAttribute("items", items);
		return "basic/items";
	}
	
	// 상품등록 페이지
	@GetMapping("/add")
	public String addForm(Model model) {
		return "basic/addForm";
	}
	
	// 상품등록에서 form내용 받기
	//@PostMapping("/add")
	public String saveV1(@RequestParam String itemName, @RequestParam int price, @RequestParam Integer quantity, Model model) {
		Item item = new Item();
		item.setItemName(itemName);
		item.setPrice(price);
		item.setQuantity(quantity);
		
		itemRepository.save(item);
		
		model.addAttribute("item",item);
		
		// 상세페이지
		return "basic/item";
	}
	
	//@PostMapping("/add")
	public String saveV2(@ModelAttribute("item")Item item) {
		// @ModelAttribute가 해주는 역할
//		Item item = new Item();
//		item.setItemName(itemName);
//		item.setPrice(price);
//		item.setQuantity(quantity);
		
		itemRepository.save(item);
		
//		model.addAttribute("item",item);
		
		// 상세페이지
		return "basic/item";
	}
	
	// redirect방식으로 상품 상세로 가도록 return 수정
	//@PostMapping("/add")
	public String saveV3(@ModelAttribute("item")Item item) {
		itemRepository.save(item);
		return "redirect:/basic/items/" + item.getId();
	}
	
	// 기존 url정보	: http://localhost:9091/basic/items/4
	// 원하는 url정보	: http://localhost:9091/basic/items/4?status=true
	@PostMapping("/add")
	public String saveV4(@ModelAttribute("item")Item item, RedirectAttributes redirectAttributes) {
		Item savedItem = itemRepository.save(item);
		
		redirectAttributes.addAttribute("itemId", savedItem.getId());	// path
		redirectAttributes.addAttribute("status", true);				// param
		
		return "redirect:/basic/items/{itemId}";
	}
	
	
	
	// url파라미터 이용하기
	@GetMapping("/{itemId}")
	public String item(@PathVariable Long itemId, Model model) {
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);
		return "basic/item";
	}
	
	// 상품수정하기
	@GetMapping("/{itemId}/edit")
	public String editForm(@PathVariable Long itemId, Model model) {
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);
		return "basic/editForm";
	}
	
	// 상품수정한 후 저장
	@PostMapping("/{itemId}/edit")
	public String edit(@PathVariable Long itemId, @ModelAttribute Item item ) {
		itemRepository.update(itemId, item);
		return "redirect:/basic/items/{itemId}";
	}
	
	/*
	 * 테스트용 데이터 추가
	 */
	@PostConstruct
	public void init() {
//		System.out.println("초기화 메서드");
		itemRepository.save(new Item("testA", 1000, 10));
		itemRepository.save(new Item("testB", 2000, 20));
		itemRepository.save(new Item("testC", 3000, 30));
	}
	
	/*
	 * 종료 메서드
	 */
//	@PreDestroy
//	public void destroy() {
//		System.out.println("종료 메서드 호출");
//	}
	
	
	
	
	
}
