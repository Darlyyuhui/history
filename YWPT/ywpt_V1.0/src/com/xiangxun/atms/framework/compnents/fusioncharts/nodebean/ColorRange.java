package com.xiangxun.atms.framework.compnents.fusioncharts.nodebean;

import java.util.List;

public class ColorRange {
	private List<Color> listcolor;

	/**
	 * @return the listcolor
	 */
	public List<Color> getListcolor() {
		return listcolor;
	}

	/**
	 * @param listcolor the listcolor to set
	 */
	public void setListcolor(List<Color> listcolor) {
		this.listcolor = listcolor;
	}

	
	@Override
	public String toString() {		
		if(listcolor==null||listcolor.size()==0){
			return "";
		}		
		StringBuilder sb = new StringBuilder();
		sb.append("<colorRange>");
		for (Color c : listcolor) {
			sb.append(c.toString());
		}
		sb.append("</colorRange>");
		return sb.toString();
	}
	
	
}
