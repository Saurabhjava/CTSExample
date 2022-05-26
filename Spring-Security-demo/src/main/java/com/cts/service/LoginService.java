package com.cts.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.dao.LoginRepository;
import com.cts.model.LoginEntity;

@Service
public class LoginService {
	@Autowired
	private LoginRepository repo;
	
	public LoginEntity getUser(String uname) {
		Optional<LoginEntity> op=repo.findById(uname);
		if(op.isPresent())
			return op.get();
		else
			return null;
	}
}
