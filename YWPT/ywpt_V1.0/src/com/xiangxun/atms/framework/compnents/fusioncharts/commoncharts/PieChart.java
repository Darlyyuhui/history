package com.xiangxun.atms.framework.compnents.fusioncharts.commoncharts;

import java.util.ArrayList;
import java.util.List;

import com.xiangxun.atms.framework.compnents.fusioncharts.commoncharts.vo.ChartDataVo;
import com.xiangxun.atms.framework.compnents.fusioncharts.nodebean.Chart;
import com.xiangxun.atms.framework.compnents.fusioncharts.nodebean.Set;




/**
 * <p>Description: com.szelink.common.fusioncharts.commoncharts+PieChart.java：这个类是处理PieChart.java信息</p>
 */
@SuppressWarnings("unchecked")
public class PieChart extends BaseChart {

	/**
	 * caption// 图表标题  
	 * showValues//是否显示数据
	 * showLabels//是否显示标签
	 * showPercentValues//是否显示百分比
	 */
	private String caption, showLabels, showValues, showPercentValues;

	private Chart chart; // 表头

	public PieChart() {
		chart = new Chart();
	}

	@Override
	public String RenderChart() {
		return super.RenderChart();
	}

	@Override
	public String RenderChart(List<? extends ChartDataVo> datalist) {
		chart.setCaption(caption);
		chart.setShowLabels(showLabels);
		chart.setShowValues(showValues);
		chart.setShowPercentValues(showPercentValues);

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
			e.setValue("1");
			listset.add(e);			
		}
		chart.setListset(listset);

		return chart.toString();
	}

	/**
	 * @return the caption
	 */
	public String getCaption() {
		return caption;
	}

	/**
	 * @param caption
	 *            the caption to set
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}

	
	/**
	 * @return the showLabels
	 */
	public String getShowLabels() {
		return showLabels;
	}

	/**
	 * @param showLabels
	 *            the showLabels to set
	 */
	public void setShowLabels(String showLabels) {
		this.showLabels = showLabels;
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
	 * @return the showPercentValues
	 */
	public String getShowPercentValues() {
		return showPercentValues;
	}

	/**
	 * @param showPercentValues
	 *            the showPercentValues to set
	 */
	public void setShowPercentValues(String showPercentValues) {
		this.showPercentValues = showPercentValues;
	}

}
