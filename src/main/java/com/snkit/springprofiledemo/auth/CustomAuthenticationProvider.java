package com.snkit.springprofiledemo.auth;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class CustomAuthenticationProvider implements AuthenticationProvider {

	
	UserDetailsService userDetailsService;

	public CustomAuthenticationProvider() {

	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		System.out.println("  CustomAuthenticationProvider Entry to  authenticate=======================   ");
		
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
		UserDetails details = userDetailsService.loadUserByUsername(auth.getName());
		System.out.println("  CustomAuthenticationProvider  authenticate=======================   ");
		return new UsernamePasswordAuthenticationToken(details.getUsername(), null, details.getAuthorities());
	}

	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	
	
}
