package com.xiangxun.atms.framework.compnents.fusioncharts;

import javax.servlet.http.HttpServletRequest;

/**
 * 画不分组的3D柱状图表
 * <p>Description: 这个类是处理趋势图的信息
 * @author:        岩涛
 */
public class SingleLine2DChart extends AbstractCharts {


	@Override
	public String drawChart(HttpServletRequest request) {
		
		String createchart=this.createChart();
		
		if(nodate){
			return Color.nodata;
		}
		
		String path=request.getContextPath()+"/compnents/fusioncharts/chart/Line.swf";
		
		return  FusionChartsCreator.createChartHTML(path, 
        		"",createchart,"Line2D",this.getWide(),
        		this.getHigh(),false);
		
	}

}
