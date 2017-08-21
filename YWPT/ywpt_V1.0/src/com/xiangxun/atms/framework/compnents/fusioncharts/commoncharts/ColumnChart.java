package com.xiangxun.atms.framework.compnents.fusioncharts.commoncharts;

import java.util.ArrayList;
import java.util.List;

import com.xiangxun.atms.framework.compnents.fusioncharts.commoncharts.vo.ChartDataVo;
import com.xiangxun.atms.framework.compnents.fusioncharts.nodebean.Categories;
import com.xiangxun.atms.framework.compnents.fusioncharts.nodebean.Category;
import com.xiangxun.atms.framework.compnents.fusioncharts.nodebean.Chart;
import com.xiangxun.atms.framework.compnents.fusioncharts.nodebean.Dataset;
import com.xiangxun.atms.framework.compnents.fusioncharts.nodebean.Set;
import com.xiangxun.atms.framework.compnents.fusioncharts.nodebean.TrendLines;



/**
 * <p>
 * Description: Description: 这个类是处理:所有类型的柱型图（单柱图、多柱图、滚动图）
 */
@SuppressWarnings("unchecked")
public class ColumnChart extends BaseChart {

	/**
	 * caption// 图表标题 
	 * subCaption //图表子标题
	 * xAxisName// 横坐标标题 
	 * yAxisName// 纵坐标标题 
	 * showValues//是否显示数据
	 * numberPrefix//纵轴数据前缀 
	 * numberSuffix //纵轴数据后缀 
	 * useRoundEdges//是否显示玻璃效果 
	 * rotateYAxisName// Y轴标题是否旋转
	 * yAxisNameWidth// Y轴标题宽度 
	 * numVisiblePlot// 滚动图可见区域内的图形数
	 * yAxisMinValue
	 * yAxisMaxValue 
	 */
	private String caption,subCaption, xAxisName, yAxisName, showValues = "0",
			numberPrefix,numberSuffix, useRoundEdges = "1", rotateYAxisName = "1",
			yAxisNameWidth = "15", numVisiblePlot = "0",yAxisMinValue,yAxisMaxValue;

	private TrendLines trendLines; // 趋势线
	private Chart chart; // 表头

	public ColumnChart() {
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

	// 单柱图
	public String RenderSingleColumnChart(List<? extends ChartDataVo> datalist) {
		chart.setCaption(caption);
		chart.setSubCaption(subCaption);
		chart.setXAxisName(xAxisName);
		chart.setYAxisName(yAxisName);
		chart.setShowValues(showValues);
		chart.setNumberPrefix(numberPrefix);
		chart.setNumberSuffix(numberSuffix);
		chart.setUseRoundEdges(useRoundEdges);
		chart.setYAxisMinValue(yAxisMinValue);
		chart.setYAxisMaxValue(yAxisMaxValue);
		
		if (rotateYAxisName.equals("0")) {
			chart.setRotateYAxisName(rotateYAxisName);
			chart.setYAxisNameWidth(yAxisNameWidth);
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

	// 滚动单柱图
	public String RenderScrollSingleColumnChart(
			List<? extends ChartDataVo> datalist) {
		chart.setCaption(caption);
		chart.setSubCaption(subCaption);
		chart.setXAxisName(xAxisName);
		chart.setYAxisName(yAxisName);
		chart.setShowValues(showValues);
		chart.setNumberPrefix(numberPrefix);
		chart.setNumberSuffix(numberSuffix);
		chart.setUseRoundEdges(useRoundEdges);
		chart.setYAxisMinValue(yAxisMinValue);
		chart.setYAxisMaxValue(yAxisMaxValue);
		if (rotateYAxisName.equals("0")) {
			chart.setRotateYAxisName(rotateYAxisName);
			chart.setYAxisNameWidth(yAxisNameWidth);
		}
		chart.setNumVisiblePlot(numVisiblePlot);

		Categories categories = new Categories();
		Dataset dataset = new Dataset();
		List<Set> listset = new ArrayList<Set>();
		List<Category> listcategory = new ArrayList<Category>();
		for (ChartDataVo vo : datalist) {
			Category c = new Category();
			c.setLabel(vo.getLabel());
			listcategory.add(c);
			Set s = new Set();
			s.setValue(vo.getValue());
			listset.add(s);
		}
		categories.setListcategory(listcategory);
		dataset.setListset(listset);
		List<Dataset> listdateset = new ArrayList<Dataset>();
		listdateset.add(dataset);

		chart.setCategories(categories);
		chart.setListdateset(listdateset);
		// 添加趋势线
		if (trendLines != null) {
			chart.setTrendLines(trendLines);
		}

		return chart.toString();
	}

	// 多柱图
	public String RenderMultiColumnChart(List<? extends ChartDataVo> datalist) {
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
	 * @param caption
	 *            the caption to set
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}

	/**
	 * @return the xAxisName
	 */
	public String getXAxisName() {
		return xAxisName;
	}

	/**
	 * @param axisName
	 *            the xAxisName to set
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
	 * @param axisName
	 *            the yAxisName to set
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
	 * @param showValues
	 *            the showValues to set
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
	 * @param numberPrefix
	 *            the numberPrefix to set
	 */
	public void setNumberPrefix(String numberPrefix) {
		this.numberPrefix = numberPrefix;
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
	 * @return the useRoundEdges
	 */
	public String getUseRoundEdges() {
		return useRoundEdges;
	}

	/**
	 * @param useRoundEdges
	 *            the useRoundEdges to set
	 */
	public void setUseRoundEdges(String useRoundEdges) {
		this.useRoundEdges = useRoundEdges;
	}

	/**
	 * @return the rotateYAxisName
	 */
	public String getRotateYAxisName() {
		return rotateYAxisName;
	}

	/**
	 * @param rotateYAxisName
	 *            the rotateYAxisName to set
	 */
	public void setRotateYAxisName(String rotateYAxisName) {
		this.rotateYAxisName = rotateYAxisName;
	}

	/**
	 * @return the yAxisNameWidth
	 */
	public String getYAxisNameWidth() {
		return yAxisNameWidth;
	}

	/**
	 * @param axisNameWidth
	 *            the yAxisNameWidth to set
	 */
	public void setYAxisNameWidth(String axisNameWidth) {
		yAxisNameWidth = axisNameWidth;
	}

	/**
	 * @return the numVisiblePlot
	 */
	public String getNumVisiblePlot() {
		return numVisiblePlot;
	}

	/**
	 * @param numVisiblePlot
	 *            the numVisiblePlot to set
	 */
	public void setNumVisiblePlot(String numVisiblePlot) {
		this.numVisiblePlot = numVisiblePlot;
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
