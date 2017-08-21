package com.xiangxun.atms.framework.compnents.fusioncharts.nodebean;

import java.lang.reflect.Field;

public class Set {
	private String label;
	private String value;
	
	private String x;
	private String y;
	private String radius;
	private String shape;
	private String name;
	private String color;
	private String id;
	private String link;
	
	public Set() {
		
	}
	
	public Set(String label, String value, String x, String y, String radius,
			String shape, String name, String color, String id, String link) {
		super();
		this.label = label;
		this.value = value;
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.shape = shape;
		this.name = name;
		this.color = color;
		this.id = id;
		this.link = link;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
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
	 * @return the x
	 */
	public String getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(String x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public String getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(String y) {
		this.y = y;
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
	 * @return the shape
	 */
	public String getShape() {
		return shape;
	}
	/**
	 * @param shape the shape to set
	 */
	public void setShape(String shape) {
		this.shape = shape;
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
	 * @return the color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}
	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<set ");
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
