package com.xiangxun.atms.framework.compnents.fusioncharts.nodebean;

import java.lang.reflect.Field;

public class Category {
	private String label; //标签，该标签会出现在X轴的图表
	private String showLabel; //是否显示标签，取值0/1，默认为1
	private String toolText;  //标签的工具提示文本

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the showLabel
	 */
	public String getShowLabel() {
		return showLabel;
	}

	/**
	 * @param showLabel the showLabel to set
	 */
	public void setShowLabel(String showLabel) {
		this.showLabel = showLabel;
	}

	/**
	 * @return the toolText
	 */
	public String getToolText() {
		return toolText;
	}

	/**
	 * @param toolText the toolText to set
	 */
	public void setToolText(String toolText) {
		this.toolText = toolText;
	}

	
	@Override
	public String toString() {		
		StringBuilder sb = new StringBuilder();
		sb.append("<category ");		
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
