package com.snkit.springprofiledemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class JPANativeEntity {
	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "email")
	private String email;
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	
	
	
}
