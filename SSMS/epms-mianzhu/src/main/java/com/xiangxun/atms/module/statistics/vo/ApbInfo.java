package com.xiangxun.atms.module.statistics.vo;

public class ApbInfo implements java.io.Serializable {

	private static final long serialVersionUID = -5919120432883420750L;

	/**
	 * 产品编号
	 */
	private String code;
	/**
	 * 产品名称
	 */
	private String name;
	/**
	 * 产品类型名称
	 */
	private String typeName;
	/**
	 * 种植产品的基地名称
	 */
	private String apbNames;
	/**
	 * 描述
	 */
	private String explain;
	
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
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getApbNames() {
		return apbNames;
	}
	public void setApbNames(String apbNames) {
		this.apbNames = apbNames;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	
}
