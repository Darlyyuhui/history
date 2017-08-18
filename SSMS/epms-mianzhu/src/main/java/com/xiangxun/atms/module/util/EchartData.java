package com.xiangxun.atms.module.util;

import java.util.List;

/**
 * EChart 数据实体
 * @author HaoXiang
 * 2017年7月13日
 */
public class EchartData implements java.io.Serializable {

	private static final long serialVersionUID = -3840902091049479714L;

	public EchartData(){}
	public EchartData(String name){
		this.name = name;
	}
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 数据列表
	 */
	private List<Object> datas;
	/**
	 * 使用Y轴坐标系的索引值
	 */
	private String yAxisIndex;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Object> getDatas() {
		return datas;
	}
	public void setDatas(List<Object> datas) {
		this.datas = datas;
	}
	public String getyAxisIndex() {
		return yAxisIndex;
	}
	public void setyAxisIndex(String yAxisIndex) {
		this.yAxisIndex = yAxisIndex;
	}
	
}
