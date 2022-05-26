package com.cts.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cts.model.LoginEntity;

@Service
public class MyUserService implements UserDetailsService {
	
	@Autowired
	private LoginService service;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("********************"+username);
		/*ArrayList<GrantedAuthority> li=new ArrayList<GrantedAuthority>();
		li.add(new SimpleGrantedAuthority("ADMIN"));*/
		LoginEntity user= service.getUser(username);
		
		return new User(user.getUserName(),user.getPassword(),AuthorityUtils.createAuthorityList(user.getRole()));
	}

}
