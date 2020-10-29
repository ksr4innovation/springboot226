package com.snkit.springprofiledemo.auth;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CustomUsernamePasswordFilter extends UsernamePasswordAuthenticationFilter {

	public CustomUsernamePasswordFilter(AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);

	}

	@Override
   public 	Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException  {
		String url = request.getRequestURI();

		
		System.out.println("  from doFilterInternal");


		String username = request.getParameter("username");
		
		String password = request.getParameter( "password");
			System.out.println("  from doFilterInternal username"+username);
			System.out.println("  from doFilterInternal password"+password);
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
			System.out.println("  from  before authenticate doFilterInternal");

         return this.getAuthenticationManager().authenticate(token);
	}




}
