package com.xiangxun.atms.framework.compnents.fusioncharts;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
/**
 * 画网络节点的图表
 * <p>Description: DragNodeChart.java ：这个类是处理DragNodeChart信息</p>
 * <p>Copyright:   Copyright (c) 2012</p>
 * <p>Company:     西安翔迅科技有限公司</p>
 * @author:        岩涛
 */
public class DragNodeChart extends AbstractCharts {
	
	private String viewMode="1";//是否确定图表视图模式或编辑模式。鉴于模式，目标用户将只能拖动节点（其中被标记为拖放能） 。编辑模式中，他们可以添加/删除节点或添加/删除连接器。
	private String allowDrag="0";//是否允许用户拖动节点
	private String xAxisMinValue;//X的下线 如果没有 指导值， FusionCharts将自动计算出最佳值为你
	private String xAxisMaxValue;//x的上线 如果没有 指导值， FusionCharts将自动计算出最佳值为你
	private String yAxisMinValue;//y的下线 如果没有 指导值， FusionCharts将自动计算出最佳值为你
	private String yAxisMaxValue;//y的上线 如果没有 指导值， FusionCharts将自动计算出最佳值为你
		
	private String showFormBtn ;//"1"在画板上显示submit BUTTON按钮
	private String formAction;//数据提交的后台ACTION
	private String formMethod;//get or post
	private String formBtnTitle;//按钮显示的名称

	public String getViewMode() {
		return viewMode;
	}

	public void setViewMode(String viewMode) {
		this.viewMode = viewMode;
	}

	public String getAllowDrag() {
		return allowDrag;
	}

	public void setAllowDrag(String allowDrag) {
		this.allowDrag = allowDrag;
	}

	public String getXAxisMinValue() {
		return xAxisMinValue;
	}

	public void setXAxisMinValue(String axisMinValue) {
		xAxisMinValue = axisMinValue;
	}

	public String getXAxisMaxValue() {
		return xAxisMaxValue;
	}

	public void setXAxisMaxValue(String axisMaxValue) {
		xAxisMaxValue = axisMaxValue;
	}

	public String getYAxisMinValue() {
		return yAxisMinValue;
	}

	public void setYAxisMinValue(String axisMinValue) {
		yAxisMinValue = axisMinValue;
	}

	public String getYAxisMaxValue() {
		return yAxisMaxValue;
	}

	public void setYAxisMaxValue(String axisMaxValue) {
		yAxisMaxValue = axisMaxValue;
	}

	
	
	public String getShowFormBtn() {
		return showFormBtn;
	}

	public void setShowFormBtn(String showFormBtn) {
		this.showFormBtn = showFormBtn;
	}

	public String getFormAction() {
		return formAction;
	}

	public void setFormAction(String formAction) {
		this.formAction = formAction;
	}

	public String getFormMethod() {
		return formMethod;
	}

	public void setFormMethod(String formMethod) {
		this.formMethod = formMethod;
	}

	public String getFormBtnTitle() {
		return formBtnTitle;
	}

	public void setFormBtnTitle(String formBtnTitle) {
		this.formBtnTitle = formBtnTitle;
	}

	@Override
	public String createRootChart() {

		StringBuffer rootchart = new StringBuffer(super.createRootChart());

		if (StringUtils.isNotEmpty(viewMode)) {
			rootchart.append(" viewMode='" + viewMode + "' ");
		}
		if (StringUtils.isNotEmpty(allowDrag)) {
			rootchart.append(" allowDrag='" + allowDrag + "' ");
		}
		if (StringUtils.isNotEmpty(xAxisMinValue)) {
			rootchart.append(" xAxisMinValue='" + xAxisMinValue + "' ");
		}
		if (StringUtils.isNotEmpty(xAxisMaxValue)) {
			rootchart.append(" xAxisMaxValue='" + xAxisMaxValue + "' ");
		}
		if (StringUtils.isNotEmpty(yAxisMinValue)) {
			rootchart.append(" yAxisMinValue='" + yAxisMinValue + "' ");
		}
		if (StringUtils.isNotEmpty(yAxisMaxValue)) {
			rootchart.append(" yAxisMaxValue='" + yAxisMaxValue + "' ");
		}
	
		if (StringUtils.isNotEmpty(showFormBtn)) {
			rootchart.append(" showFormBtn='" + showFormBtn + "' ");
		}
		if (StringUtils.isNotEmpty(formAction)) {
			rootchart.append(" formAction='" + formAction + "' ");
		}
		if (StringUtils.isNotEmpty(formMethod)) {
			rootchart.append(" formMethod='" + formMethod + "' ");
		}
		if (StringUtils.isNotEmpty(formBtnTitle)) {
			rootchart.append(" formBtnTitle='" + formBtnTitle + "' ");
		}
		return rootchart.toString();
	}

	@Override
	public String drawChart(HttpServletRequest request) {
		
		
		String createchart=this.createChart();
		
		if(nodate){
			return Color.nodata;
		}
		
		
		String path = request.getContextPath()
				+ "/fusioncharts/chart/DragNode.swf";

		return FusionChartsCreator.createChartHTML(path, "",
				createchart, "DragNode", this.getWide(), this
						.getHigh(), false);
	}

}
