package com.xiangxun.atms.framework.compnents.fusioncharts.commoncharts;

import java.util.List;

import com.xiangxun.atms.framework.compnents.fusioncharts.commoncharts.vo.ChartDataVo;



public abstract class BaseChart {
	
	public String RenderChart(){return null;};
	public String RenderChart(List<? extends ChartDataVo> datalist){return null;};
	
	
	

}
