package com.xiangxun.atms.framework.compnents.fusioncharts.nodebean;

import java.lang.reflect.Field;

public class Dial {
	private String value;
	private String borderAlpha;
	private String bgColor;
	private String baseWidth;
	private String topWidth;
	private String radius;
	
	
	public Dial() {
	
	}
	
	public Dial(String value, String borderAlpha, String bgColor,
			String baseWidth, String topWidth, String radius) {
		this.value = value;
		this.borderAlpha = borderAlpha;
		this.bgColor = bgColor;
		this.baseWidth = baseWidth;
		this.topWidth = topWidth;
		this.radius = radius;
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
	/**
	 * @return the borderAlpha
	 */
	public String getBorderAlpha() {
		return borderAlpha;
	}
	/**
	 * @param borderAlpha the borderAlpha to set
	 */
	public void setBorderAlpha(String borderAlpha) {
		this.borderAlpha = borderAlpha;
	}
	/**
	 * @return the bgColor
	 */
	public String getBgColor() {
		return bgColor;
	}
	/**
	 * @param bgColor the bgColor to set
	 */
	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}
	/**
	 * @return the baseWidth
	 */
	public String getBaseWidth() {
		return baseWidth;
	}
	/**
	 * @param baseWidth the baseWidth to set
	 */
	public void setBaseWidth(String baseWidth) {
		this.baseWidth = baseWidth;
	}
	/**
	 * @return the topWidth
	 */
	public String getTopWidth() {
		return topWidth;
	}
	/**
	 * @param topWidth the topWidth to set
	 */
	public void setTopWidth(String topWidth) {
		this.topWidth = topWidth;
	}
	/**
	 * @return the radius
	 */
	public String getRadius() {
		return radius;
	}
	/**
	 * @param radius the radius to set
	 */
	public void setRadius(String radius) {
		this.radius = radius;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<dial ");		
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
