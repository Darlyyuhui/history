package com.xiangxun.atms.common.dictionary.vo;

import java.io.Serializable;

/***
 * key/value封装类
 * @author zhouhaij
 * @May 2, 2013 10:45:44 AM
 */
public class KeyValue implements Serializable{

	private static final long serialVersionUID = 1L;

	private String key;
	
	private String value;
	
	public KeyValue() {
		super();
	}

	public KeyValue(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
}
