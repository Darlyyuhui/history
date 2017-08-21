package com.xiangxun.atms.framework.compnents.fusioncharts;

import javax.servlet.http.HttpServletRequest;

/**
 * 多条趋线图
 * @author:        岩涛
 */
public class MsLine2DChart extends AbstractCharts {


	@Override
	public String drawChart(HttpServletRequest request) {
		
		String createchart=this.createChart();
		
		if(nodate){
			return Color.nodata;
		}
		
		String path=request.getContextPath()+"/compnents/fusioncharts/chart/MSLine.swf";
		
		return  FusionChartsCreator.createChartHTML(path, 
        		"",createchart,"MsLine2DChart",this.getWide(),
        		this.getHigh(),false);
		
	}

}
