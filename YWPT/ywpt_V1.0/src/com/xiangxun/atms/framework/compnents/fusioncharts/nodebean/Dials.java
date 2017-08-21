package com.xiangxun.atms.framework.compnents.fusioncharts.nodebean;

import java.util.List;

public class Dials {
	private List<Dial> listdial;

	/**
	 * @return the listdial
	 */
	public List<Dial> getListdial() {
		return listdial;
	}

	/**
	 * @param listdial the listdial to set
	 */
	public void setListdial(List<Dial> listdial) {
		this.listdial = listdial;
	}

	
	@Override
	public String toString() {
		if(listdial==null||listdial.size()==0){
			return "";
		}		
		StringBuilder sb = new StringBuilder();
		sb.append("<dials>");
		for (Dial c : listdial) {
			sb.append(c.toString());
		}
		sb.append("</dials>");
		return sb.toString();
	}
	

}
