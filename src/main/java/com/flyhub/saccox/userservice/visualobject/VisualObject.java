package com.flyhub.saccox.userservice.visualobject;

public class VisualObject {	
	
	private Boolean success;
	
	private String error;
	
	private String message;
	
	private Object data;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public VisualObject() {
	}

	public VisualObject(Boolean success, String error, String message, Object data) {
		this.success = success;
		this.error = error;
		this.message = message;
		this.data = data;
	}	
}
