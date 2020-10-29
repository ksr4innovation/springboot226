package com.snkit.springprofiledemo.auth;

import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class SignInAuthenticationToken extends AbstractAuthenticationToken {

	private String token;
	private final Object principal;
	private Object credentials;

	public SignInAuthenticationToken(Object principal, Object credentials, String token) {
		super(null);
		this.principal = principal;
		this.credentials = credentials;
		this.token = token;
		setAuthenticated(false);
	}

	public SignInAuthenticationToken(Object principal, Object credentials, String token,
			Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
		this.credentials = credentials;
		this.token = token;
		setAuthenticated(true);
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

}
