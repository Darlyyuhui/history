package com.xiangxun.atms.common.user.vo;

import java.io.Serializable;

/***
 * 数据权限封装类
 * @author zhouhaij
 * @May 7, 2013 9:16:03 AM
 */
public class Modules  implements Serializable{
	private static final long serialVersionUID = -7262132677739205271L;

	/***
	 * 功能编码
	 */
	private String code;
	
	/***
	 * 功能名称
	 */
	private String name;
	
	/***
	 * 是否可用  1为可用
	 */
	private String flag;

	/**
	 * @param code
	 * @param name
	 * @param flag
	 */
	public Modules(String code, String name, String flag) {
		super();
		this.code = code;
		this.name = name;
		this.flag = flag;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
	
	
}
