package com.xiangxun.atms.framework.compnents.fusioncharts.nodebean;

import java.lang.reflect.Field;

public class AnnotationGroup extends AnnotationGroupNodes {
	private String xPos;
	private String yPos;
	
	
	public AnnotationGroup() {
		
	}
	
	public AnnotationGroup(String pos, String pos2) {
		xPos = pos;
		yPos = pos2;
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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<annotationGroup ");
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
		sb.append(">");		
		if(getListannotation()!=null){
			for(Annotation a:getListannotation()){
				sb.append(a.toString());
			}
		}		
		sb.append("</annotationGroup>");
		return sb.toString();
	}
	

}
