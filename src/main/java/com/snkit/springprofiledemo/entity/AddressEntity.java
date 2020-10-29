package com.snkit.springprofiledemo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "microservice", name = "user_address")
public class AddressEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7728877930957427413L;

	public AddressEntity() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "addid")
	private Long addId;

	@Column(name = "street")
	private String street;

	@Column(name = "country")
	private String country;

	@Column(name = "userid", insertable = false, updatable = false)
	private Long userId;

	@Column(name = "status")
	private String status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	private CustomUserEntity userEntity;

	public Long getAddId() {
		return addId;
	}

	public void setAddId(Long addId) {
		this.addId = addId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public CustomUserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(CustomUserEntity userEntity) {
		this.userEntity = userEntity;
	}

	@Override
	public String toString() {
		return "AddressEntity [street=" + street + ", country=" + country + ", userId=" + userId + ", status=" + status
				+ "]";
	}

}
