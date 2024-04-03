package edu.kh.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@GetMapping("test1")
	public String test1() {
		
		
		return "test1";
	}
	
	@GetMapping("test2")
	public String test2(Model model) {
		
		model.addAttribute("str", "<h1>테스트 중 &times; </h1>");
		
		return "test2";
	}
}
