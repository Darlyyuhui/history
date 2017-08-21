package com.xiangxun.atms.framework.compnents.fusioncharts.commoncharts;

import java.util.ArrayList;
import java.util.List;

import com.xiangxun.atms.framework.compnents.fusioncharts.commoncharts.vo.ChartDataVo;
import com.xiangxun.atms.framework.compnents.fusioncharts.nodebean.Categories;
import com.xiangxun.atms.framework.compnents.fusioncharts.nodebean.Category;
import com.xiangxun.atms.framework.compnents.fusioncharts.nodebean.Chart;
import com.xiangxun.atms.framework.compnents.fusioncharts.nodebean.Set;
import com.xiangxun.atms.framework.compnents.fusioncharts.nodebean.TrendLines;




/**
 * <p>Description: com.szelink.common.fusioncharts.commoncharts+BarChart.java：这个类是处理BarChart.java信息</p>
 */
@SuppressWarnings("unchecked")
public class BarChart extends BaseChart {

	/**
	 * caption// 图表标题 
	 * subCaption //图表子标题
	 * xAxisName// 纵坐标标题 
	 * yAxisName// 横坐标标题 
	 * showValues//是否显示数据 0/1
	 * numberPrefix//横轴数据前缀 
	 * useRoundEdges//是否显示玻璃效果 0/1 
	 * rotateXAxisName// Y轴标题是否旋转 0/1
	 * xAxisNameWidth// Y轴标题宽度 
	 * showLabels //是否显示xAxis标签 0/1
	 * numberSuffix //纵轴数据后缀 
	 * yAxisMinValue
	 * yAxisMaxValue 
	 */
	private String caption,subCaption, xAxisName, yAxisName, showValues = "0",
			numberPrefix, useRoundEdges = "1", rotateXAxisName="1",
			xAxisNameWidth = "15",showLabels,numberSuffix,yAxisMinValue,yAxisMaxValue;

	private TrendLines trendLines; // 趋势线
	private Chart chart; // 表头

	public BarChart() {
		chart = new Chart();
	}

	@Override
	public String RenderChart() {
		return super.RenderChart();
	}

	@Override
	public String RenderChart(List<? extends ChartDataVo> datalist) {
		return super.RenderChart(datalist);
	}

	/**
	 * 
	 * @param datalist
	 * @return
	 */
	public String RenderSingleBarChart(List<? extends ChartDataVo> datalist) {
		chart.setCaption(caption);
		chart.setSubCaption(subCaption);
		chart.setXAxisName(xAxisName);
		chart.setYAxisName(yAxisName);
		chart.setShowValues(showValues);
		chart.setNumberPrefix(numberPrefix);
		chart.setUseRoundEdges(useRoundEdges);
		chart.setShowLabels(showLabels);
		chart.setNumberSuffix(numberSuffix);
		chart.setYAxisMinValue(yAxisMinValue);
		chart.setYAxisMaxValue(yAxisMaxValue);
		if(rotateXAxisName.equals("0")){
			chart.setRotateXAxisName(rotateXAxisName);		
			chart.setXAxisNameWidth(xAxisNameWidth);
		}

		List<Set> listset = new ArrayList<Set>();
		for (ChartDataVo vo : datalist) {
			Set e = new Set();
			e.setLabel(vo.getLabel());
			e.setValue(vo.getValue());
			listset.add(e);
		}
		if(datalist.size()==0){
			Set e = new Set();
			e.setLabel("无数据");
			e.setValue("0");
			listset.add(e);
		}
		chart.setListset(listset);
		
		// 添加趋势线
		if (trendLines != null) {
			chart.setTrendLines(trendLines);
		}

		return chart.toString();
	}

	/**
	 * 
	 * @param datalist
	 * @return
	 */
	public String RenderMultiBarChart(List<? extends ChartDataVo> datalist) {
		Categories categories = new Categories();
		List<Category> listcategory = new ArrayList<Category>();
		for (ChartDataVo vo : datalist) {
			Category e = new Category();
			e.setLabel(vo.getLabel());
			listcategory.add(e);
		}
		categories.setListcategory(listcategory);
		return chart.toString();
	}

	
	/**
	 * @return the caption
	 */
	public String getCaption() {
		return caption;
	}

	/**
	 * @param caption the caption to set
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}

	/**
	 * @return the subCaption
	 */
	public String getSubCaption() {
		return subCaption;
	}

	/**
	 * @param subCaption the subCaption to set
	 */
	public void setSubCaption(String subCaption) {
		this.subCaption = subCaption;
	}

	/**
	 * @return the xAxisName
	 */
	public String getXAxisName() {
		return xAxisName;
	}

	/**
	 * @param axisName the xAxisName to set
	 */
	public void setXAxisName(String axisName) {
		xAxisName = axisName;
	}

	/**
	 * @return the yAxisName
	 */
	public String getYAxisName() {
		return yAxisName;
	}

	/**
	 * @param axisName the yAxisName to set
	 */
	public void setYAxisName(String axisName) {
		yAxisName = axisName;
	}

	/**
	 * @return the showValues
	 */
	public String getShowValues() {
		return showValues;
	}

	/**
	 * @param showValues the showValues to set
	 */
	public void setShowValues(String showValues) {
		this.showValues = showValues;
	}

	/**
	 * @return the numberPrefix
	 */
	public String getNumberPrefix() {
		return numberPrefix;
	}

	/**
	 * @param numberPrefix the numberPrefix to set
	 */
	public void setNumberPrefix(String numberPrefix) {
		this.numberPrefix = numberPrefix;
	}

	/**
	 * @return the useRoundEdges
	 */
	public String getUseRoundEdges() {
		return useRoundEdges;
	}

	/**
	 * @param useRoundEdges the useRoundEdges to set
	 */
	public void setUseRoundEdges(String useRoundEdges) {
		this.useRoundEdges = useRoundEdges;
	}

	/**
	 * @return the rotateXAxisName
	 */
	public String getRotateXAxisName() {
		return rotateXAxisName;
	}

	/**
	 * @param rotateXAxisName the rotateXAxisName to set
	 */
	public void setRotateXAxisName(String rotateXAxisName) {
		this.rotateXAxisName = rotateXAxisName;
	}

	/**
	 * @return the xAxisNameWidth
	 */
	public String getXAxisNameWidth() {
		return xAxisNameWidth;
	}

	/**
	 * @param axisNameWidth the xAxisNameWidth to set
	 */
	public void setXAxisNameWidth(String axisNameWidth) {
		xAxisNameWidth = axisNameWidth;
	}

	/**
	 * @return the showLabels
	 */
	public String getShowLabels() {
		return showLabels;
	}

	/**
	 * @param showLabels the showLabels to set
	 */
	public void setShowLabels(String showLabels) {
		this.showLabels = showLabels;
	}
	
	

	/**
	 * @return the numberSuffix
	 */
	public String getNumberSuffix() {
		return numberSuffix;
	}

	/**
	 * @param numberSuffix the numberSuffix to set
	 */
	public void setNumberSuffix(String numberSuffix) {
		this.numberSuffix = numberSuffix;
	}

	/**
	 * @return the yAxisMinValue
	 */
	public String getYAxisMinValue() {
		return yAxisMinValue;
	}

	/**
	 * @param axisMinValue the yAxisMinValue to set
	 */
	public void setYAxisMinValue(String axisMinValue) {
		yAxisMinValue = axisMinValue;
	}

	/**
	 * @return the yAxisMaxValue
	 */
	public String getYAxisMaxValue() {
		return yAxisMaxValue;
	}

	/**
	 * @param axisMaxValue the yAxisMaxValue to set
	 */
	public void setYAxisMaxValue(String axisMaxValue) {
		yAxisMaxValue = axisMaxValue;
	}

	/**
	 * @return the trendLines
	 */
	public TrendLines getTrendLines() {
		return trendLines;
	}

	/**
	 * @param trendLines
	 *            the trendLines to set
	 */
	public void setTrendLines(TrendLines trendLines) {
		this.trendLines = trendLines;
	}

}
