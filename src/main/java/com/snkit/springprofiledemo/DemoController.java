package com.snkit.springprofiledemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/rest/employee")
public class DemoController {

	List<Employee> employee = new ArrayList();

	@PostConstruct
	public void data() {
		employee.add(new Employee(String.valueOf(1), "ABC", "Hyd", "India"));
		employee.add(new Employee(String.valueOf(2), "BBB", "Bang", "India"));
		employee.add(new Employee(String.valueOf(3), "CCC", "Bang", "India"));
		employee.add(new Employee(String.valueOf(4), "DDD", "Hyd", "India"));
		employee.add(new Employee(String.valueOf(5), "XYZ", "Hyd", "India"));
	}

	
	
	 @ApiOperation(value = "get active Employee",
			 nickname = "getEmployeeGet", 
			 notes = "Active Employe",
			 response = EmployeeResponse.class)
    @ApiResponses(value = { 
        @ApiResponse(code = 200, 
        		message = "successful operation", 
        		response = EmployeeResponse.class),
        @ApiResponse(code = 404, 
        		message = "Not found result", 
        		response = EmployeeResponse.class)}
    )
    @RequestMapping(value = "/employee/getEmployeeGet",
        produces = { "application/json", "application/xml" }, 
        method = RequestMethod.GET)	
	public ResponseEntity<EmployeeResponse> getEmployeeGet( ) {
		EmployeeResponse resp = new EmployeeResponse();
		employee.forEach(emp -> resp.addEmploye(emp));
		return new ResponseEntity(resp, HttpStatus.OK);
	}

	@GetMapping(
			produces = { "application/json", "application/xml" })
	public ResponseEntity<EmployeeResponseV2> getEmployeeV2() {
		
		Authentication authentication =	SecurityContextHolder.getContext().getAuthentication();
		EmployeeResponseV2 resp = new EmployeeResponseV2();
		resp.setCompanyName(authentication.getName());
		employee.forEach(emp -> resp.addEmploye(emp));
		return new ResponseEntity(resp, HttpStatus.OK);
	}

	@Autowired
	EmployeService empService;
	
	/*
	 * @GetMapping(value = "/{xyz}", consumes = { "application/vnd.snkit-v1+json" },
	 * produces = { "application/json", "application/xml" }) public
	 * ResponseEntity<Employee> getEmployeeByIdPathName(
	 * 
	 * @PathVariable(name = "xyz") String id) { Optional<Employee> empOptional =
	 * empService.findEmployeeById(id); Employee emp = null; if
	 * (empOptional.isPresent()) { emp = empOptional.get(); } return new
	 * ResponseEntity(emp, HttpStatus.OK); }
	 */
	
	@GetMapping(value = "/{id}",
			produces = { "application/json",
			"application/xml" })
	public ResponseEntity<Employee> getEmployeeById(
			@PathVariable String id) {
		Optional<Employee> empOptional = empService.findEmployeeById(id);
		Employee emp = null;
		if (empOptional.isPresent()) {
			emp = 	empOptional.get();
		}
		return new ResponseEntity(emp, HttpStatus.OK);
	}

	
	@GetMapping(
			consumes = { "application/vnd.snkit-v2+json" },
			produces = { "application/json",
			"application/xml" })
	public ResponseEntity<Employee> getEmployeeByIdReqParam(
			@RequestParam String id) {

		List<Employee> empList = employee.stream()
				.filter(emp -> emp.getId().equalsIgnoreCase(id))
				.collect(Collectors.toList());
		Employee emp = null;
		if (Objects.nonNull(empList) && !empList.isEmpty()) {
			emp = empList.get(0);
		}

		return new ResponseEntity(emp, HttpStatus.OK);
	}
	
	
	@PostMapping(consumes = {"application/json"},
			produces = { "application/json", "application/xml" })
	public ResponseEntity<EmployeeResponse> addEmploye(
			@RequestBody Employee emp ) {
		employee.add(emp);
		EmployeeResponse resp = new EmployeeResponse();
		employee.forEach(employee -> resp.addEmploye(employee));
		return new ResponseEntity(resp, HttpStatus.OK);
	}
}
