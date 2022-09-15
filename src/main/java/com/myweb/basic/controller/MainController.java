package com.myweb.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	//메인화면
	@GetMapping("/main")
	public String main() {
		return "main"; //main.html
	}
	
	
}