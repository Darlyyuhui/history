package com.xiangxun.atms.framework.compnents.fusioncharts.nodebean;

import java.lang.reflect.Field;

public class Annotation {
	private String type;
	private String xPos;
	private String yPos;
	private String radius;
	private String startAngle;
	private String endAngle;
	private String fillPattern;
	private String fillAsGradient;
	private String fillColor;
	private String fillAlpha;
	private String fillRatio;
	private String fillDegree;
	private String showBorder;
	private String borderColor;
	private String borderThickness;
	
	
	public Annotation() {

	}
	
	public Annotation(String type, String xPos, String yPos, String radius,
			String startAngle, String endAngle, String fillPattern,
			String fillAsGradient, String fillColor, String fillAlpha,
			String fillRatio, String fillDegree, String showBorder,
			String borderColor, String borderThickness) {
		super();
		this.type = type;
		this.xPos = xPos;
		this.yPos = yPos;
		this.radius = radius;
		this.startAngle = startAngle;
		this.endAngle = endAngle;
		this.fillPattern = fillPattern;
		this.fillAsGradient = fillAsGradient;
		this.fillColor = fillColor;
		this.fillAlpha = fillAlpha;
		this.fillRatio = fillRatio;
		this.fillDegree = fillDegree;
		this.showBorder = showBorder;
		this.borderColor = borderColor;
		this.borderThickness = borderThickness;
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
	 * @return the xPos
	 */
	public String getXPos() {
		return xPos;
	}
	/**
	 * @param pos the xPos to set
	 */
	public void setXPos(String pos) {
		xPos = pos;
	}
	/**
	 * @return the yPos
	 */
	public String getYPos() {
		return yPos;
	}
	/**
	 * @param pos the yPos to set
	 */
	public void setYPos(String pos) {
		yPos = pos;
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
	/**
	 * @return the startAngle
	 */
	public String getStartAngle() {
		return startAngle;
	}
	/**
	 * @param startAngle the startAngle to set
	 */
	public void setStartAngle(String startAngle) {
		this.startAngle = startAngle;
	}
	/**
	 * @return the endAngle
	 */
	public String getEndAngle() {
		return endAngle;
	}
	/**
	 * @param endAngle the endAngle to set
	 */
	public void setEndAngle(String endAngle) {
		this.endAngle = endAngle;
	}
	/**
	 * @return the fillPattern
	 */
	public String getFillPattern() {
		return fillPattern;
	}
	/**
	 * @param fillPattern the fillPattern to set
	 */
	public void setFillPattern(String fillPattern) {
		this.fillPattern = fillPattern;
	}
	/**
	 * @return the fillAsGradient
	 */
	public String getFillAsGradient() {
		return fillAsGradient;
	}
	/**
	 * @param fillAsGradient the fillAsGradient to set
	 */
	public void setFillAsGradient(String fillAsGradient) {
		this.fillAsGradient = fillAsGradient;
	}
	/**
	 * @return the fillColor
	 */
	public String getFillColor() {
		return fillColor;
	}
	/**
	 * @param fillColor the fillColor to set
	 */
	public void setFillColor(String fillColor) {
		this.fillColor = fillColor;
	}
	/**
	 * @return the fillAlpha
	 */
	public String getFillAlpha() {
		return fillAlpha;
	}
	/**
	 * @param fillAlpha the fillAlpha to set
	 */
	public void setFillAlpha(String fillAlpha) {
		this.fillAlpha = fillAlpha;
	}
	/**
	 * @return the fillRatio
	 */
	public String getFillRatio() {
		return fillRatio;
	}
	/**
	 * @param fillRatio the fillRatio to set
	 */
	public void setFillRatio(String fillRatio) {
		this.fillRatio = fillRatio;
	}
	/**
	 * @return the fillDegree
	 */
	public String getFillDegree() {
		return fillDegree;
	}
	/**
	 * @param fillDegree the fillDegree to set
	 */
	public void setFillDegree(String fillDegree) {
		this.fillDegree = fillDegree;
	}
	/**
	 * @return the showBorder
	 */
	public String getShowBorder() {
		return showBorder;
	}
	/**
	 * @param showBorder the showBorder to set
	 */
	public void setShowBorder(String showBorder) {
		this.showBorder = showBorder;
	}
	/**
	 * @return the borderColor
	 */
	public String getBorderColor() {
		return borderColor;
	}
	/**
	 * @param borderColor the borderColor to set
	 */
	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}
	/**
	 * @return the borderThickness
	 */
	public String getBorderThickness() {
		return borderThickness;
	}
	/**
	 * @param borderThickness the borderThickness to set
	 */
	public void setBorderThickness(String borderThickness) {
		this.borderThickness = borderThickness;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<annotation ");		
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
