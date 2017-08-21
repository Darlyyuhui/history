package com.xiangxun.atms.framework.compnents.fusioncharts;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Description: 这个类是处理柱状组合图 + 线性图 的处理信息
 * 可以按照数据集合 分别放置到页面上，也可以直接生成${barchartStr}放置到页面
 * @author: 岩涛
 */
public class SingleColumn3DLineChart extends AbstractCharts {


	@Override
	public String drawChart(HttpServletRequest request) {
		
		String createchart=this.createChart();
		
		if(nodate){
			return Color.nodata;
		}
		
		String path=request.getContextPath()+"/compnents/fusioncharts/chart/MSColumn3DLineDY.swf";
		
		return  FusionChartsCreator.createChartHTML(path,"",createchart,"MSColumn3DLineDY",this.getWide(),this.getHigh(),false);
		
	}

}
