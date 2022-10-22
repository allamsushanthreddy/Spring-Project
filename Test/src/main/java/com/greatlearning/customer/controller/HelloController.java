package com.greatlearning.customer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/")
public class HelloController {
	
	@RequestMapping("/test")
	public String showMainPage() {
		return "demo";
	}
}
