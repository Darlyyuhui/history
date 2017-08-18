package com.xiangxun.ywpt.mobile.business.domain;

/**
 * 数据字典
 * @author HaoXiang
 * 2017年3月2日
 */
public class Dic {

	public Dic(){}
	public Dic(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	private String code;
	private String name;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
