package com.xiangxun.atms.framework.compnents.fusioncharts;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
/**
 * 画不分组的饼状图表
 * <p>Description: 这个类是处理SinglePieColumn3DChart信息</p>
 * @author:        岩涛
 * @date:          2012-03-05 
 */
public class SinglePieColumn3DChart extends AbstractCharts {

	private String showPercentageValues = "0";// 是否显示百分比值在标签的图表上（饼状图上的属性）。
	private String startingAngle;//画饼图的起始角度
	private String slicingDistance= "1";;//当点击时 当前饼离开中心轴
	private String enableSmartLabels;//是否开启标明显
	private String enableRotation; //开始旋转

	
	public String getShowPercentageValues() {
		return showPercentageValues;
	}

	public void setShowPercentageValues(String showPercentageValues) {
		this.showPercentageValues = showPercentageValues;
	}

	public String getStartingAngle() {
		return startingAngle;
	}

	public void setStartingAngle(String startingAngle) {
		this.startingAngle = startingAngle;
	}
	public String getSlicingDistance() {
		return slicingDistance;
	}

	public void setSlicingDistance(String slicingDistance) {
		this.slicingDistance = slicingDistance;
	}

	public String getEnableSmartLabels() {
		return enableSmartLabels;
	}

	public void setEnableSmartLabels(String enableSmartLabels) {
		this.enableSmartLabels = enableSmartLabels;
	}

	public String getEnableRotation() {
		return enableRotation;
	}

	public void setEnableRotation(String enableRotation) {
		this.enableRotation = enableRotation;
	}

	@Override
	public String createRootChart() {

		StringBuffer rootchart = new StringBuffer(super.createRootChart());

		if (StringUtils.isNotEmpty(showPercentageValues)) {
			rootchart.append(" showPercentageValues='" + showPercentageValues+ "' ");
		}
		if (StringUtils.isNotEmpty(startingAngle)) {
			rootchart.append(" startingAngle='" + startingAngle+ "' ");
		}
		if (StringUtils.isNotEmpty(slicingDistance)) {
			rootchart.append(" slicingDistance='" + slicingDistance+ "' ");
		}
		if (StringUtils.isNotEmpty(enableSmartLabels)) {
			rootchart.append(" enableSmartLabels='" + enableSmartLabels+ "' ");
		}
		if (StringUtils.isNotEmpty(enableRotation)) {
			rootchart.append(" enableRotation='" + enableRotation+ "' ");
		}
		return rootchart.toString();
	}

	@Override
	public String drawChart(HttpServletRequest request) {
		
		String createchart=this.createChart();
		
		if(nodate){
			return Color.nodata;
		}
		
		String path = request.getContextPath()+"/compnents/fusioncharts/chart/Pie2D.swf";

		return FusionChartsCreator.createChartHTML(path, "",createchart, "Pie3D", this.getWide(), this.getHigh(),false);
	}

}
