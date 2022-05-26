package com.cts.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("admin")
	public String adminPanel() {
		return "Admin Panel";
	}
	@GetMapping("user")
	public String userPanel() {
		return "User Panel";
	}
	@GetMapping("/404")
	public String errorPage() {
		return "You are not authorized user";
	}

}
