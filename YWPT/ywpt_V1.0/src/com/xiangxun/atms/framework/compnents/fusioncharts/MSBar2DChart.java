package com.xiangxun.atms.framework.compnents.fusioncharts;

import javax.servlet.http.HttpServletRequest;

/**
 * @author:        岩涛 MSBar2D.swf
 */
public class MSBar2DChart extends AbstractCharts {


	@Override
	public String drawChart(HttpServletRequest request) {
		
		String createchart=this.createChart();
		
		if(nodate){
			return Color.nodata;
		}
		
		String path=request.getContextPath()+"/compnents/fusioncharts/chart/MSBar2D.swf";
		
		return  FusionChartsCreator.createChartHTML(path, 
        		"",createchart,"MSBar2D",this.getWide(),
        		this.getHigh(),false);
		
	}

}
