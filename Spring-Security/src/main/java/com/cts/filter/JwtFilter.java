package com.cts.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cts.service.MyUserDetailService;
import com.cts.util.JWTUtil;

@Component
public class JwtFilter extends OncePerRequestFilter {
	@Autowired
	private JWTUtil jwt;
	@Autowired
	private MyUserDetailService userService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authorization = request.getHeader("Authorization");
		String token=null;
		String userName=null;
		if(authorization!=null && authorization.startsWith("Bearer ")) {
			token=authorization.substring(7);
			userName=jwt.getUsernameFromToken(token);
		}
		if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails uDetails=userService.loadUserByUsername(userName);
			if(jwt.validateToken(token, uDetails)) {
				System.out.println("======Validated=======");
				UsernamePasswordAuthenticationToken upToken=new UsernamePasswordAuthenticationToken(uDetails,null,uDetails.getAuthorities());
				upToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(upToken);
			}
		}
		filterChain.doFilter(request, response);
	}
	
}
