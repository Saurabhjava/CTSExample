package com.cts.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cts.model.MyUser;
import com.cts.model.UserRepository;

@Service
public class MyUserDetailService implements UserDetailsService {
	@Autowired
	private UserRepository uRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("**************"+username);
		Optional<MyUser> op=uRepo.findById(username);
		if(op.isPresent()) {
			MyUser u=op.get();
			System.out.println(u.getUserName()+"\t"+u.getPassword());
			return new User(u.getUserName(),u.getPassword(), new ArrayList<>());
		}
		else {
			throw new UsernameNotFoundException("User Not Available with UserName:"+username);
		}
	
		
	}

}
