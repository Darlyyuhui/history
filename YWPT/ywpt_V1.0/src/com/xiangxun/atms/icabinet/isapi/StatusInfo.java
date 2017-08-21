package com.xiangxun.atms.icabinet.isapi;

public class StatusInfo {
	private StatusCode statusCode = StatusCode.Unknown;
	private String message;

	public StatusCode getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(StatusCode statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}		
	
}


