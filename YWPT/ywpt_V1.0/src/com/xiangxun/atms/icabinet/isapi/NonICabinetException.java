package com.xiangxun.atms.icabinet.isapi;

/**
 * 目标主机不是机柜
 * @author Administrator
 *
 */
public class NonICabinetException extends Exception {

	private static final long serialVersionUID = 1L;

	public NonICabinetException(String message) {
		super(message);
	}

	public NonICabinetException(Exception ex) {
		super(ex);
	}

}
