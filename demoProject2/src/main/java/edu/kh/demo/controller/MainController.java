package edu.kh.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// Controller : 요청 / 응답 제어
//			   `요청 ~ 요청에 따라 알맞은 서비스 호출 할지 제어
//			   `응답 ~ 서비스 결과에 따라 어떤 응답을 할지 제어

@Controller // 요청/응답 제어 역할 명시 + Bean으로 등록(스프링이 만들어줘~ 객체로 관리해줘)
public class MainController {
	
	// "/" 주소 요청 시 해당 메서드와 매핑
	// 메인페이지 지정 시에는 슬래시 사용 가능
	@RequestMapping("/") 
	public String mainPage() {
		
		
		// thymeleaf : Spring Boot에서 사용하는 템플릿 엔진
		// thymeleaf를 이용한 html 파일로 forward 시 사용되는 접두사, 접미사 존재
		// 접두 : classpath:/templates/
		// 접미 : .html
		return "common/main";
	}

}
