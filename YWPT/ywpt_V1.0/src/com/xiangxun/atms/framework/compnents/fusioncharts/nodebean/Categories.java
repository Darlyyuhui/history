package com.xiangxun.atms.framework.compnents.fusioncharts.nodebean;

import java.util.List;

public class Categories {
	private List<Category> listcategory;

	/**
	 * @return the listcategory
	 */
	public List<Category> getListcategory() {
		return listcategory;
	}

	/**
	 * @param listcategory the listcategory to set
	 */
	public void setListcategory(List<Category> listcategory) {
		this.listcategory = listcategory;
	}

	
	@Override
	public String toString() {		
		if(listcategory.size()==0){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("<categories>");
		for (Category c : listcategory) {
			sb.append(c.toString());
		}
		sb.append("</categories>");
		return sb.toString();
	}
	
	
}
