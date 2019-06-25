package com.restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class homeController {

	@RequestMapping("/")
	public String home() {
		
		return "home";
		
	}
	
	@RequestMapping("/entrar")
	public String entrar() {
		
		return "login";
		
	}
	
}
