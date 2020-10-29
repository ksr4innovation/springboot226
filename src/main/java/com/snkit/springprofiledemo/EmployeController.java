package com.snkit.springprofiledemo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snkit.springprofiledemo.entity.JPANativeEntity;
import com.snkit.springprofiledemo.repository.CustomUserRepository;

@RestController
public class EmployeController {

	@Autowired
	CustomUserRepository customUserRepository;
	
	@GetMapping(value = "/getEmploye")
	public List<JPANativeEntity> getEmploye(){
		return customUserRepository.getJpaNativeEntityList();
	}
	
	@GetMapping(value = "/getEmployeInterface")
	public List<CustomUserRepository.CustInfo> getEmployeInterface(){
		return customUserRepository.getCustomerInfo();
	}
}
