package com.snkit.springprofiledemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service(value = "empService")
public class EmployeService {

	
	List<Employee> employee = new ArrayList();

	@PostConstruct
	public void data() {
		employee.add(new Employee(String.valueOf(1), "ABC", "Hyd", "India"));
		employee.add(new Employee(String.valueOf(2), "BBB", "Bang", "India"));
		employee.add(new Employee(String.valueOf(3), "CCC", "Bang", "India"));
		employee.add(new Employee(String.valueOf(4), "DDD", "Hyd", "India"));
		employee.add(new Employee(String.valueOf(5), "XYZ", "Hyd", "India"));
	}
	
	public Optional<Employee> findEmployeeById(String id) {
		List<Employee> empList = employee.stream()
				.filter(emp -> emp.getId().equalsIgnoreCase(id))
				.collect(Collectors.toList());
		
		Employee emp = null;
		if (Objects.nonNull(empList) && !empList.isEmpty()) {
			emp = empList.get(0);
		}
		
		if (emp == null) {
			String str = "Employee not found for "+id;
			throw new EmployeeNotFoundRuntimeException(str,"E001", 
					UUID.randomUUID().toString(),"W");
		}
		return Optional.of(emp);
		
	}
}
