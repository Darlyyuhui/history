package com.xiangxun.atms.icabinet.isapi;

public class PutException extends Exception {

	private static final long serialVersionUID = 1L;

	public PutException() {
		this("提交失败,请检查参数的合理性");
	}

	public PutException(String message) {
		super(message);
	}

	public PutException(Exception ex) {
		super(ex);
	}
}
