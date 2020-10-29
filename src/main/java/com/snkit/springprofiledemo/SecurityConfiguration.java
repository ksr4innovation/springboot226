package com.snkit.springprofiledemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.snkit.springprofiledemo.auth.CustomAuthenticationProvider;
import com.snkit.springprofiledemo.auth.CustomBasicFilter;
import com.snkit.springprofiledemo.auth.CustomUsernamePasswordFilter;
import com.snkit.springprofiledemo.auth.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	public void configure(WebSecurity web) throws Exception {
		web.debug(true);
	}

	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/healthCheck").permitAll()
		.antMatchers("/login").permitAll()
		.antMatchers("/getEmploye").permitAll()
		.antMatchers("/getEmployeInterface").permitAll()
		.antMatchers("/**").authenticated();
	//	http.addFilterBefore(customBasicFilter(), BasicAuthenticationFilter.class);
	//	http.httpBasic();
		http.addFilterBefore(customUsernamePasswordFilter(), UsernamePasswordAuthenticationFilter.class);
		http.formLogin();
	}
	
	@Bean
	public CustomUsernamePasswordFilter customUsernamePasswordFilter() throws Exception {
		
		CustomUsernamePasswordFilter custom = new CustomUsernamePasswordFilter(authenticationManagerBean()) ;
		return custom;
	}
	
	/*
	 * @Bean public CustomBasicFilter customBasicFilter() throws Exception {
	 * 
	 * CustomBasicFilter custom = new CustomBasicFilter(authenticationManagerBean())
	 * ; return custom; }
	 */

	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.authenticationProvider(customAuthenticationProvider());

	}

	@Bean
	public CustomAuthenticationProvider customAuthenticationProvider() {
		CustomAuthenticationProvider provider = new CustomAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		return provider;
	}

	@Bean
	public UserDetailsServiceImpl userDetailsService() {
		return new UserDetailsServiceImpl();
	}
}
