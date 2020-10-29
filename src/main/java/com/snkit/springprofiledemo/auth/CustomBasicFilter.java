package com.snkit.springprofiledemo.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class CustomBasicFilter extends BasicAuthenticationFilter {
	private AuthenticationManager authenticationManager;

	public CustomBasicFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		this.authenticationManager = authenticationManager;

	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String url = request.getRequestURI();

		String header = request.getHeader("Authorization");
		
		System.out.println("  from doFilterInternal");

		if (header != null && header.startsWith("Basic ")) {

			String[] tokens = extractAndDecodeHeader(header, request);
			System.out.println("  from doFilterInternal tokens[0]"+tokens[0]);
			System.out.println("  from doFilterInternal tokens[1]"+tokens[1]);
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(tokens[0], tokens[1]);
			System.out.println("  from  before authenticate doFilterInternal");
			Authentication auth = this.getAuthenticationManager().authenticate(token);
			System.out.println("  from  Post authenticate doFilterInternal");
			SecurityContextHolder.getContext().setAuthentication(auth);

		}
		chain.doFilter(request, response);

	}

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	private String[] extractAndDecodeHeader(String header, HttpServletRequest request) throws IOException {

		byte[] base64Token = header.substring(6).getBytes("UTF-8");
		byte[] decoded;
		try {
			decoded = Base64.decode(base64Token);
		} catch (IllegalArgumentException e) {
			throw new BadCredentialsException("Failed to decode basic authentication token");
		}
		String token = new String(decoded, "UTF-8");
		int delim = token.indexOf(":");

		if (delim == -1) {
			throw new BadCredentialsException("Invalid basic authentication token");
		}
		return new String[] { token.substring(0, delim), token.substring(delim + 1) };
	}
}
