package com.xiangxun.atms.framework.compnents.fusioncharts.nodebean;

import java.util.List;

public class Application {
	private List<Apply> listapply;

	/**
	 * @return the listapply
	 */
	public List<Apply> getListapply() {
		return listapply;
	}

	/**
	 * @param listapply the listapply to set
	 */
	public void setListapply(List<Apply> listapply) {
		this.listapply = listapply;
	}
	
	@Override
	public String toString() {
		if(listapply.size()==0){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("<application>");
		for (Apply a : listapply) {
			sb.append(a.toString());
		}
		sb.append("</application>");
		return sb.toString();
	}

}
