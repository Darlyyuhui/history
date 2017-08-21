package com.xiangxun.atms.framework.compnents.fusioncharts.nodebean;

import java.lang.reflect.Field;

public class Dataset extends DatasetNodes{
	private String seriesName;

	/**
	 * @return the seriesName
	 */
	public String getSeriesName() {
		return seriesName;
	}

	/**
	 * @param seriesName
	 *            the seriesName to set
	 */
	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<dataset ");
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
		if(getListset()!=null){
			for(Set s:getListset()){
				sb.append(s.toString());
			}
		}		
		sb.append("</dataset>");
		return sb.toString();
	}
	
	

}
