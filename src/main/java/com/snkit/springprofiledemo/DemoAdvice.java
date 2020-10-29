package com.snkit.springprofiledemo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class DemoAdvice {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<RootResponse> errorHandler(RuntimeException e) {
		List<Error> errorList = new ArrayList();
		RootResponse root = new RootResponse();
		Error error = new Error();
		error.setMessage(e.getMessage());
		errorList.add(error);
		root.setError(errorList);

		return new ResponseEntity(root, HttpStatus.OK);
	}
	@ExceptionHandler(EmployeeNotFoundRuntimeException.class)
	public ResponseEntity<RootResponse> errorHandler(EmployeeNotFoundRuntimeException e) {
		List<Error> errorList = new ArrayList();
		RootResponse root = new RootResponse();
		root.setCorrelationId(e.getCorelationId());
		Error error = new Error();
		error.setCode(e.getCode());
		error.setMessage(e.getMessage());
		errorList.add(error);
		root.setError(errorList);
		root.setErrorType(e.getErrorSevirity());

		return new ResponseEntity(root, HttpStatus.OK);
	}
	
	@ExceptionHandler({HttpMediaTypeNotSupportedException.class})
	public final ResponseEntity<Object> handleException(HttpMediaTypeNotSupportedException ex, WebRequest request) throws Exception {
		List<Error> errorList = new ArrayList();
		RootResponse root = new RootResponse();
		String uuid = UUID.randomUUID().toString();
		System.out.println(uuid);
		root.setCorrelationId(uuid);
		Error error = new Error();
		error.setCode("SE001");
		error.setMessage(ex.getMessage());
		errorList.add(error);
		root.setError(errorList);
		root.setErrorType("W");

		return new ResponseEntity(root, HttpStatus.OK);
		
	}
	
	
}
