package com.snkit.springprofiledemo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;


@SqlResultSetMappings(value= {
		@SqlResultSetMapping(name="EmployeResultMapping",
				entities= {
				@EntityResult(entityClass=JPANativeEntity.class,fields= {
						@FieldResult(name="id",column="id"),
						@FieldResult(name="email",column="email")
				})
				})
		})


@NamedNativeQueries(value = {
		@NamedNativeQuery(name = "getEmploye",
				query = "select id, email  from microservice.user",
				resultSetMapping = "EmployeResultMapping") })

@Entity
@Table(schema = "microservice", name = "user")
public class CustomUserEntity implements Serializable {

	private static final long serialVersionUID = -8763977161007689038L;

	public CustomUserEntity() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "email")
	private String email;

	@Column(name = "name")
	private String name;

	// Bidirectional relationship
	/*
	 * @OneToMany(cascade = CascadeType.ALL, mappedBy = "userEntity", fetch =
	 * FetchType.LAZY) private List<AddressEntity> addressList = new
	 * ArrayList<AddressEntity>();
	 */

	// UniDirectional relationship
	
	  @OneToMany(cascade = CascadeType.ALL, targetEntity=AddressEntity.class, 
			  fetch= FetchType.LAZY)
	  @JoinColumn(name="userid",referencedColumnName="id")
	  private List<AddressEntity> addressList = new ArrayList<AddressEntity>();
	 
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AddressEntity> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<AddressEntity> addressList) {
		this.addressList = addressList;
	}

	@Override
	public String toString() {
		return "UserEntity [email=" + email + ", name=" + name + "]";
	}

}
