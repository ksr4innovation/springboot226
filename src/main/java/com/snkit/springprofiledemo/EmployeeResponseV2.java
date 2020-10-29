package com.snkit.springprofiledemo;

import java.util.ArrayList;
import java.util.List;

public class EmployeeResponseV2 {
	public List<Employee> employee;

	public EmployeeResponseV2() {

	}
	
	private String companyName;
	
	

	public List<Employee> getEmployee() {
		return employee;
	}

	public EmployeeResponseV2 addEmploye(Employee emp) {
		if (employee == null) {
			employee = new ArrayList<>();
		}
		employee.add(emp);
		return this;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
}
