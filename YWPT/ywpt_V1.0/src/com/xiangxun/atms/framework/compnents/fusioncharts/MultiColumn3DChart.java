package com.xiangxun.atms.framework.compnents.fusioncharts;

import javax.servlet.http.HttpServletRequest;

/**
 * 画成组的3D柱状图表
 * <p>Description: ：这个类是处理MultiColumn3DChart信息</p>
 */
public class MultiColumn3DChart extends AbstractCharts {


	@Override
	public String drawChart(HttpServletRequest request) {
		
		String createchart=this.createChart();
		
		if(nodate){
			return Color.nodata;
		}
		
		String path=request.getContextPath()+"/compnents/fusioncharts/chart/MSColumn3D.swf";
		
		return  FusionChartsCreator.createChartHTML(path, 
        		"",createchart,"MSColumn3D",this.getWide(),
        		this.getHigh(),false);
		
	}

}
