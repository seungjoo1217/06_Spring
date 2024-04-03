package edu.kh.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// Controller : 요청에 따라 알맞은 서비스 호출 할지 제어
//				+ 서비스 결과에 따라 어떤 응답을 할지 제어

//요청/응답 제어 역할 명시 + Bean 등록
@Controller // IOC(제어의 역전)
public class MainController {

	// "/" 주소 요청 시 해당 메서드와 매핑
	// - 메인 페이지 지정시에는 "/" 작성 가능
	@RequestMapping("/")
	public String mainPage() {
		
		// forward : 요청 위임
		
		// thymeleaf : Spring Boot에서 사용하는 템플릿 엔진
		
		// thymleaf를 이용한 html 파일로 forward 시
		// 사용되는 접두사, 접미사가 존재
		
		// 접두사 : classpath:/templates/
		// 접미사 : .html
		// src/main/resources/templates/common/main.html
		
		return "common/main";
	}
	
}
