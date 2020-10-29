package com.snkit.springprofiledemo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="microservice",name="role")
public class RoleEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6105148664600158206L;

	public RoleEntity() {
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="roleid")
	private Long roleId;
	
	@Column(name="rolename")
	private String roleName;

}
