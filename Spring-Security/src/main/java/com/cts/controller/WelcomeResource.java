package com.cts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.model.JwtRequest;
import com.cts.model.JwtResponse;
import com.cts.service.MyUserDetailService;
import com.cts.util.JWTUtil;

@RestController
public class WelcomeResource {
	@Autowired
	private AuthenticationManager authManager; 
	@Autowired
	private MyUserDetailService userService;
	@Autowired
	private JWTUtil jwt;
	@RequestMapping("/hello")
	public String helloWorld() {
		return "Hello Spring Security";
	}
	@RequestMapping("/admin")
	public String helloWorld1() {
		return "Hello You able to see beacuse you are Admin";
	}
	@RequestMapping("/403")
	public String errorPage() {
		return "Hello! You are not authorized for this resource.";
	}
	@PostMapping("/authenticate")
	public JwtResponse authenticate(@RequestBody JwtRequest req) throws Exception {
		System.out.println("=======Called================");
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(),req.getPassword()));
		}
		catch(BadCredentialsException ex) {
			throw new Exception("Invaid User Details..");
		}
		final UserDetails uDetails=userService.loadUserByUsername(req.getUsername());
		final String token=jwt.generateToken(uDetails);
		return new JwtResponse(token);
	}
	
}
