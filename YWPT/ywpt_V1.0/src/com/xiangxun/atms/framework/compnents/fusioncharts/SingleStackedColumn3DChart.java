package com.xiangxun.atms.framework.compnents.fusioncharts;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Description: 这个类是处理StackedColumn3D堆栈图信息
 * @author:        岩涛
 */
public class SingleStackedColumn3DChart extends AbstractCharts {
	
	

	@Override
	public String drawChart(HttpServletRequest request) {
		
		String createchart=this.createChart();
		
		if(nodate){
			return Color.nodata;
		}
		
		String path=request.getContextPath()+"/compnents/fusioncharts/chart/StackedColumn3D.swf";
		
		return  FusionChartsCreator.createChartHTML(path, 
        		"",createchart,"StackedColumn3D",this.getWide(),
        		this.getHigh(),false);
		
	}

}
