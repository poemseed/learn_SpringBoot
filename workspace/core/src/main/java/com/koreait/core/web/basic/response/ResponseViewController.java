package com.koreait.core.web.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

	
	@RequestMapping("/response-view-v1")
	public ModelAndView responseViewV1() {
		ModelAndView mav = new ModelAndView("response/hello").addObject("data", "hello!");
		return mav;
	}
	
	/*
	 * @Controller에서 return이 String이면 view의 논리적 이름이 된다.
	 * @ResponseBody가 붙으면 문자열을 return한다. view를 return하지 않는다.
	 */
	//@ResponseBody
	@RequestMapping("/response-view-v2")
	public String responseViewV2(Model model) {
		model.addAttribute("data", "model Hello!!");
		return "response/hello";
	}
	
	
}
