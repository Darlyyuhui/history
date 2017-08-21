package com.xiangxun.atms.framework.compnents.fusioncharts.nodebean;

import java.util.List;

public class Definition {
	private List<Style> liststyle;

	/**
	 * @return the liststyle
	 */
	public List<Style> getListstyle() {
		return liststyle;
	}

	/**
	 * @param liststyle the liststyle to set
	 */
	public void setListstyle(List<Style> liststyle) {
		this.liststyle = liststyle;
	}

	
	@Override
	public String toString() {
		if(liststyle.size()==0){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("<definition>");
		for (Style s : liststyle) {
			sb.append(s.toString());
		}
		sb.append("</definition>");
		return sb.toString();
	}
	
}
