package com.cts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.model.JWTRequest;
import com.cts.model.JWTResponse;
import com.cts.service.MyUserService;
import com.cts.util.JWTUtility;

@RestController
public class JWTAuthenticationController {
	
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private JWTUtility jwt;
	@Autowired
	private MyUserService uservice;
	
	@PostMapping(value="/authenticate")
	public JWTResponse createToken(@RequestBody JWTRequest request ) throws Exception {
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		}
		catch(BadCredentialsException ex) {
			throw new Exception("Invalid Credential",ex);
		}
		UserDetails udetail=uservice.loadUserByUsername(request.getUsername());
		final String token=jwt.generateToken(udetail);
		return new JWTResponse(token);
	}
}
