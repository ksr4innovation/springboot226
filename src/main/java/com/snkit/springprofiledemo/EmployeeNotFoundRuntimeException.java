package com.snkit.springprofiledemo;

public class EmployeeNotFoundRuntimeException extends RuntimeException {
	private String code;
	private String corelationId;
	private String errorSevirity;
	
	public EmployeeNotFoundRuntimeException(String msg,String code,
			String corelationId,String errorSevirity
			) {
		super(msg);
		this.code = code;
		this.corelationId =corelationId;
		this.errorSevirity = errorSevirity;
		
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
