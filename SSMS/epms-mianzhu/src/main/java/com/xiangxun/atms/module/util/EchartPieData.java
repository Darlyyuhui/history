package com.xiangxun.atms.module.util;

/**
 * 饼图数据类
 * @author HaoXiang
 * 2017年7月28日
 */
public class EchartPieData implements java.io.Serializable {

	private static final long serialVersionUID = 1693671897093733757L;

	public EchartPieData(String name, Object data) {
		this.name = name;
		this.data = data;
	}
	
	private String name;
	private Object data;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
