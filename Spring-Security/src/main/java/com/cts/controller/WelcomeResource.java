package com.cts.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeResource {
	@RequestMapping("/hello")
	public String helloWorld() {
		return "Hello Spring Security";
	}
	@RequestMapping("/admin")
	public String helloWorld1() {
		return "Hello You able to see beacuse you are Admin";
	}
	
}
