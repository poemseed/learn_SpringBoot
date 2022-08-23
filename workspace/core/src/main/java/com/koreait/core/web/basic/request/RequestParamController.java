package com.koreait.core.web.basic.request;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.core.web.basic.dto.HelloData;

@Controller
public class RequestParamController {
	
	// 반환 타입이 없고 응답에 값을 직접 집어넣으면, view 조회X
	@RequestMapping("/request-param-v1")
	public void requestParamV1( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		String username = request.getParameter("username");
		int age = Integer.parseInt(request.getParameter("age"));
		
		System.out.println("username : " + username );
		System.out.println("age : " + age );
		response.getWriter().write("ok");
		
	}
	
	/*
	 * @ResponseBody
	 *  - view 조회를 무시하고 HTTP message body에 직접 내용을 할당
	 *  @RequestParam
	 *   - 파라미터 이름으로 바인딩
	 *   @RequestParam("username") String memberName
	 *    -> String memberName =  request.getParameter("username");
	 */
	@ResponseBody
	@RequestMapping("/request-param-v2")
	public String requestParamV2( @RequestParam("username") String memberName, @RequestParam("age") int memberAge ){
		
		System.out.println("username : " + memberName );
		System.out.println("age : " + memberAge );
		
		return "ok";
	}
	
	/*
	 * @RequestParam
	 *  - HTTP 파라미터 이름이 변수 이름과 같으면 @RequestParam(name="...") 생략 가능
	 */
	@ResponseBody
	@RequestMapping("/request-param-v3")
	public String requestParamV3( @RequestParam String username, @RequestParam int age ){
		
		System.out.println("username : " + username );
		System.out.println("age : " + age );
		
		return "v3";
	}
	
	/*
	 * @RequestParam
	 *  - String, int, Integer, ... 등의 단순 타입이면 @RequestParam도 생략 가능 -> 권장하지 않음
	 */
	@ResponseBody
	@RequestMapping("/request-param-v4")
	public String requestParamV4( String username, int age ){
		
		System.out.println("username : " + username );
		System.out.println("age : " + age );
		
		return "v4";
	}
	
	/*
	 * @RequestParam.required = true
	 *  - 반드시 파라미터 값이 들어와야한다.
	 *  @RequestParam.required = false
	 *   - 생략가능
	 */
	@ResponseBody
	@RequestMapping("/request-param-required")
	public String requestParamRequired( @RequestParam(required = true) String username, 
										@RequestParam(required = false) Integer age ){	//null값이 나와야하니깐 Integer 사용
		
		System.out.println("username : " + username );
		System.out.println("age : " + age );
		
		return "Required";
	}
	
	/*
	 * RequestParam.defaultValue
	 *  - 기본값
	 *  - 빈 문자의 경우에도 적용
	 */
	@ResponseBody
	@RequestMapping("/request-param-default")
	public String requestParamDefault( @RequestParam(required = true, defaultValue = "guest") String username, 
										@RequestParam(required = false, defaultValue = "-1") int age ){
		
		System.out.println("username : " + username );
		System.out.println("age : " + age );
		
		return "Default";
	}
	
	/*
	 * 
	 */
	@ResponseBody
	@RequestMapping("/request-param-map")
	public String requestParamMap( @RequestParam Map<String, Object> paramMap ){
		
		System.out.println("username : " + paramMap.get("username"));
		System.out.println("age : " + paramMap.get("age"));
		
		return "Map";
	}
	
	/*
	 * 요청 파라미터를 받아서 필요한 객체를 만들고 그 객체에 값을 넣어준다.
	 * 
	 * @RequestParam String username;
	 * @RequestParam int age;
	 * 
	 * MemberDTO member = new MemberDTO();
	 * member.setUsername(username);
	 * member.setAge(age);
	 * 
	 * => @ModelAttribute
	 * 
	 */
	
	// 사용전
//	@ResponseBody
//	@RequestMapping("/model-attribute-v1")
//	public String modelAttributeV1(@RequestParam String username, @RequestParam int age){
//		
//		HelloData helloData = new HelloData();
//		helloData.setUsername(username);
//		helloData.setAge(age);
//		
//		System.out.println("username : " + helloData.getUsername() );
//		System.out.println("age : " + helloData.getAge() );
//		
//		return "model-V1";
//	}
	
	/*
	 * @ModelAttribute 사용으로 간단하게 처리
	 *  ->  @RequestParam String username, @RequestParam int age
	 *  	HelloData helloData = new HelloData();
	 *  	helloData.setUsername(username);
	 *  	helloData.setAge(age);
	 */
	// 사용 후
	@ResponseBody
	@RequestMapping("/model-attribute-v1")
	public String modelAttributeV1( @ModelAttribute HelloData helloData ){
		
		System.out.println("username : " + helloData.getUsername() );
		System.out.println("age : " + helloData.getAge() );
		
		return "model-V1";
	}
	
	/*
	 * @ModelAttribute 생략 가능
	 *  - @RequestParam와 @ModelAttribute 생략시 혼란 발생할 수 있다.
	 *  - @RequestParam		: String, int, Integer 같은 단순타입
	 *  - @ModelAttribute	: 나머지
	 */
	@ResponseBody
	@RequestMapping("/model-attribute-v2")
	public String modelAttributeV2( HelloData helloData ){
		
		System.out.println("username : " + helloData.getUsername() );
		System.out.println("age : " + helloData.getAge() );
		
		return "model-V2";
	}
	
	
	
	
	
}
