package com.xiangxun.atms.framework.validator;

/**
 * 返回实体
 * @author zhouhaij
 * @Apr 23, 2013 9:36:09 AM
 */
public class ResponseEntity {

	/***
	 * 返回结果
	 */
	private String result;
	
	/***
	 * 返回状态，便于跟踪超时
	 */
	private String status;
	
	/***
	 * 错误消息
	 */
	private String message;

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
