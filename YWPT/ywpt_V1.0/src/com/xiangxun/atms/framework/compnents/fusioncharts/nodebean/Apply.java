package com.xiangxun.atms.framework.compnents.fusioncharts.nodebean;

import java.lang.reflect.Field;

public class Apply {
	private String toObject;
	private String tyles;
	/**
	 * @return the toObject
	 */
	public String getToObject() {
		return toObject;
	}
	/**
	 * @param toObject the toObject to set
	 */
	public void setToObject(String toObject) {
		this.toObject = toObject;
	}
	/**
	 * @return the tyles
	 */
	public String getTyles() {
		return tyles;
	}
	/**
	 * @param tyles the tyles to set
	 */
	public void setTyles(String tyles) {
		this.tyles = tyles;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<apply ");		
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
