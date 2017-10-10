package com.xiangxun.epms.mobile.business.domain;

public class NewestCode {
	private String tableName;
	private String code;
	private String conttion ;
	private String value;
	public String getTableName() {
		return tableName;
	}
	public NewestCode(String TableName,String conttion,String value){
		this.tableName=TableName;
		this.conttion=conttion;
		this.value=value;
	}
	public NewestCode() {
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getConttion() {
		return conttion;
	}
	public void setConttion(String conttion) {
		this.conttion = conttion;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
