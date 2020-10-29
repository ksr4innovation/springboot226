package com.snkit.springprofiledemo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer", schema = "microservice")
public class CustomerEntity implements Serializable {

	public CustomerEntity() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "addhar")
	private String addhar;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userEntity", fetch = FetchType.LAZY)
	private List<AddressEntity> addressList = new ArrayList<AddressEntity>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddhar() {
		return addhar;
	}

	public void setAddhar(String addhar) {
		this.addhar = addhar;
	}

}
