package com.xiangxun.atms.framework.compnents.fusioncharts.nodebean;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: 处理fusionchart图表的trendLines节点</p>
 * <p>Copyright:   Copyright (c) 2012</p>
 * <p>Company:     深圳市易聆科信息技术有限公司</p>
 * @author:        岩涛
 */
public class TrendLines {
	private List<Line> listline;	

	public TrendLines() {
		listline=new ArrayList<Line>();
	}

	public void addline(Line e) {
		listline.add(e);
	}

	/**
	 * @return the listline
	 */
	public List<Line> getListline() {
		return listline;
	}

	/**
	 * @param listline the listline to set
	 */
	public void setListline(List<Line> listline) {
		this.listline = listline;
	}

	@Override
	public String toString() {	
		if(listline.size()==0){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("<trendLines>");
		for (Line e : listline) {
			sb.append(e.toString());
		}
		sb.append("</trendLines>");
		return sb.toString();
	}

}
