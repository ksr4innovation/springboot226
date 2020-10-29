package com.snkit.springprofiledemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.snkit.springprofiledemo.entity.JPANativeEntity;
import com.snkit.springprofiledemo.entity.CustomUserEntity;

@Repository
public interface CustomUserRepository extends JpaRepository<CustomUserEntity, Long> ,JpaSpecificationExecutor<CustomUserEntity>{

	

	@Query(name = "getEmploye", nativeQuery = true)
	public List<JPANativeEntity> getJpaNativeEntityList();

	@Query(value = "select id, email  from microservice.user", nativeQuery = true)
	public List<CustInfo> getCustomerInfo();

	interface CustInfo {
		Integer getId();

		String getEmail();
	};
	
	@Query("select usr from CustomUserEntity usr "
			+ " left outer join usr.addressList addr where usr.id =:id ")
	public List<CustomUserEntity> getUserDataActiveAdd(@Param("id") Long id);
	

	@Query("select usr from CustomUserEntity usr"
			+ "  join fetch usr.addressList add where usr.id =:id ")
	public List<CustomUserEntity> getUserData(@Param("id") Long id);

}
