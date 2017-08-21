package com.xiangxun.atms.framework.compnents.fusioncharts.nodebean;

import java.lang.reflect.Field;

public class Style {
	private String name;
	private String type;
	private String param;
	private String start;
	private String duration;
	
	private String isHTML;
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the param
	 */
	public String getParam() {
		return param;
	}
	/**
	 * @param param the param to set
	 */
	public void setParam(String param) {
		this.param = param;
	}
	/**
	 * @return the start
	 */
	public String getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(String start) {
		this.start = start;
	}
	/**
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}
	/**
	 * @param duration the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}
	/**
	 * @return the isHTML
	 */
	public String getIsHTML() {
		return isHTML;
	}
	/**
	 * @param isHTML the isHTML to set
	 */
	public void setIsHTML(String isHTML) {
		this.isHTML = isHTML;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<style ");		
		Field[] fs =getClass().getDeclaredFields();
		for(Field f:fs){
			try {
				if (f.get(this) != null) {
					sb.append(f.getName() + "='" + f.get(this)+"' ");
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}		
		sb.append("/>");
		return sb.toString();
	}
	
	
	
	
}
