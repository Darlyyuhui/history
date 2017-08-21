package com.xiangxun.atms.framework.compnents.fusioncharts.nodebean;

import java.lang.reflect.Field;

public class Line {
	private String startValue;
	private String color;
	private String displayvalue;

	private String endValue;
	private String alpha;
	private String isTrendZone;
	
	

	public Line() {
		
	}

	public Line(String startValue, String color, String displayvalue,
			String endValue, String alpha, String isTrendZone) {
		this.startValue = startValue;
		this.color = color;
		this.displayvalue = displayvalue;
		this.endValue = endValue;
		this.alpha = alpha;
		this.isTrendZone = isTrendZone;
	}

	/**
	 * @return the startValue
	 */
	public String getStartValue() {
		return startValue;
	}

	/**
	 * @param startValue
	 *            the startValue to set
	 */
	public void setStartValue(String startValue) {
		this.startValue = startValue;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return the displayvalue
	 */
	public String getDisplayvalue() {
		return displayvalue;
	}

	/**
	 * @param displayvalue
	 *            the displayvalue to set
	 */
	public void setDisplayvalue(String displayvalue) {
		this.displayvalue = displayvalue;
	}

	/**
	 * @return the endValue
	 */
	public String getEndValue() {
		return endValue;
	}

	/**
	 * @param endValue
	 *            the endValue to set
	 */
	public void setEndValue(String endValue) {
		this.endValue = endValue;
	}

	/**
	 * @return the alpha
	 */
	public String getAlpha() {
		return alpha;
	}

	/**
	 * @param alpha
	 *            the alpha to set
	 */
	public void setAlpha(String alpha) {
		this.alpha = alpha;
	}

	/**
	 * @return the isTrendZone
	 */
	public String getIsTrendZone() {
		return isTrendZone;
	}

	/**
	 * @param isTrendZone
	 *            the isTrendZone to set
	 */
	public void setIsTrendZone(String isTrendZone) {
		this.isTrendZone = isTrendZone;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<line ");
//		Class<line> c=line.class;
//		Field[] fs =c.getDeclaredFields(); 
//		for (int i = 0; i < fs.length; i++) {			
//			try {
//				if (fs[i].get(this) != null) {
//					sb.append(fs[i].getName() + "=\"" + fs[i].get(this)+"\" ");
//				}
//			} catch (IllegalArgumentException e) {
//				e.printStackTrace();
//			} catch (IllegalAccessException e) {
//				e.printStackTrace();
//			}
//		}
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
