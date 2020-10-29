package com.snkit.springprofiledemo;

public class EmployeeNotFoundException extends Exception {
	
	private String code;
	private String msg;
	
	private String corelationId;
	private String errorSevirity;
	
	public EmployeeNotFoundException(String msg,String code,
			String corelationId,String errorSevirity
			) {
		this.code = code;
		this.msg = msg;
		this.corelationId =corelationId;
		this.errorSevirity = errorSevirity;
		
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCorelationId() {
		return corelationId;
	}

	public void setCorelationId(String corelationId) {
		this.corelationId = corelationId;
	}

	public String getErrorSevirity() {
		return errorSevirity;
	}

	public void setErrorSevirity(String errorSevirity) {
		this.errorSevirity = errorSevirity;
	}

}
