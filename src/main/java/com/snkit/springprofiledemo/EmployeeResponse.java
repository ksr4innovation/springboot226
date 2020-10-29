package com.snkit.springprofiledemo;

import java.util.ArrayList;
import java.util.List;

public class EmployeeResponse extends RootResponse {

	public List<Employee> employee;

	public EmployeeResponse() {

	}

	public List<Employee> getEmployee() {
		return employee;
	}

	public EmployeeResponse addEmploye(Employee emp) {
		if (employee == null) {
			employee = new ArrayList<>();
		}
		employee.add(emp);
		return this;
	}

}
