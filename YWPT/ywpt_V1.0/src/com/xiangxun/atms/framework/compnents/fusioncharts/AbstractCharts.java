package com.xiangxun.atms.framework.compnents.fusioncharts;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
/**
 * 图表的基类，里面封装的图表的共性属性
 * Description: 这个类是处理AbstractCharts信息
 * @author:     岩涛
 */
public abstract class AbstractCharts {

	private int wide;// 画布的宽
	private int high;// 画布的高

	private String caption;// 标题
	private String subcaption;// 副标题
	private String xAxisName;// x坐标名称
	private String yAxisName;// y坐标名称
	private String yAxisMaxValue;//Y坐标轴的最大刻度
	private String yAxisMinValue;//Y坐标轴的最小刻度
    private String bgColor;
    private String anchorRadius;//半径

	

	private String formatNumber;// 格式数字40,000 if formatNumber='1' and 40000 if
	// formatNumber='0 '\
	private String decimalPrecision="2";//画布内数字的精度，小数点后的位数
	private String numberPrefix;// 数字的前缀 you could specify this attribute to '
	// $' to show like $40000, $50000.
	private String numberSuffix;// 数字的后缀you could specify this attribute to '
	// /a' to show like 40000/a, 50000/a
	private String rotateYAxisName = "0";// 旋转坐标的名称If you do not wish to rotate
	// y-axis name, set this as 0

	private String baseFont = "微软雅黑";// 整个画布的字体
	private String baseFontSize = "12";// 整个画布的字体大小
	private String baseFontColor;// 整个画布的字体颜色
	
	private String outCnvBaseFont= "微软雅黑";// 整个画布外的字体
	private String outCnvBaseFontSize = "12";// 整个画布外的字体大小
	private String outCnvBaseFontColor ;//整个画布外的字体颜色
	
	private String chartLeftMargin="5";//设置图表左边距
	private String chartRightMargin;// 设置图表右边距
	private String chartTopMargin;//设置图表上边距
	private String chartBottomMargin;//设置图表下边距 
	private String shownames;   //设置是否在x轴下显示<set>里指定的name
	private String showValues;  //设置是否在柱型图或饼型图上显示数据的值
	private String formatNumberScale;
	
	
	private String animation;//是否显示开场动画
	private String showBorder;//显示边框
	private String borderColor;//边框颜色
	
	
	//双Y轴组合图特性属性
	private String PYAxisName;
	private String SYAxisName;
	private String rotateValues;
	
	
	//趋势面积图特性
	private String divlinecolor;
	private String numdivlines;
	private String areaBorderColor;
	private String numVDivLines;
	private String vDivLineAlpha;
	private String rotateNames;
	
	//横坐标显示方式 
	private String labelDisplay;
	private String slantLabels;
	
	

	public boolean nodate = false;
	private String bodyxml;// 图表的xml文件体

	/**
	 * 创建XML文件
	 * 
	 * @return
	 */
	public String createChart() {

		String rootchart = this.createRootChart();

		StringBuffer chart = new StringBuffer(rootchart);

		chart.append(" >");

		if (StringUtils.isNotEmpty(bodyxml)) {
			chart.append(bodyxml);
		} else {
			nodate=true;
		}

		chart.append(" </chart>");

		return chart.toString();

	}

	/**
	 * 创建图表的XML文件头
	 * @return
	 */
	public String createRootChart() {
		StringBuffer rootchart = new StringBuffer("<chart ");

		if (StringUtils.isNotEmpty(caption)) {
			rootchart.append(" caption='" + caption + "' ");
			rootchart.append(" aboutMenuItemLabel='西安翔迅科技有限公司' ");
			rootchart.append(" aboutMenuItemLink='' ");
			rootchart.append(" exportEnabled='1' exportAtClient='1' exportHandler='fcExporter1' exportFileName='"+new java.util.Date().getTime()+"' exportDialogMessage='正在绘制图片，请稍候...'  ");
		}
		if (StringUtils.isNotEmpty(subcaption)) {
			rootchart.append(" subcaption='" + subcaption + "' ");
		}
		if (StringUtils.isNotEmpty(xAxisName)) {
			rootchart.append(" xAxisName='" + xAxisName + "' ");
		}
		if (StringUtils.isNotEmpty(yAxisName)) {
			rootchart.append(" yAxisName='" + yAxisName + "' ");
		}
		if(StringUtils.isNotEmpty(bgColor)){
			rootchart.append(" bgColor='" + bgColor + "' ");
		}
		if (StringUtils.isNotEmpty(formatNumber)) {
			rootchart.append(" formatNumber='" + formatNumber + "' ");
		}
		if (StringUtils.isNotEmpty(numberPrefix)) {
			rootchart.append(" numberPrefix='" + numberPrefix + "' ");
		}
		if (StringUtils.isNotEmpty(numberSuffix)) {
			rootchart.append(" numberSuffix='" + numberSuffix + "' ");
		}
		if (StringUtils.isNotEmpty(rotateYAxisName)) {
			rootchart.append(" rotateYAxisName='" + rotateYAxisName + "' ");
		}
		if (StringUtils.isNotEmpty(baseFont)) {
			rootchart.append(" baseFont='" + baseFont + "' ");
		}
		if (StringUtils.isNotEmpty(baseFontSize)) {
			rootchart.append(" baseFontSize='" + baseFontSize + "' ");
		}
		if (StringUtils.isNotEmpty(baseFontColor)) {
			rootchart.append(" baseFontColor='" + baseFontColor + "' ");
		}
		if (StringUtils.isNotEmpty(yAxisMaxValue)) {
			rootchart.append(" yAxisMaxValue='" + yAxisMaxValue + "' ");
		}
		if (StringUtils.isNotEmpty(outCnvBaseFont)) {
			rootchart.append(" outCnvBaseFont='" + outCnvBaseFont + "' ");
		}
		if (StringUtils.isNotEmpty(yAxisMinValue)) {
			rootchart.append(" yAxisMinValue='" + yAxisMinValue + "' ");
		}
		if (StringUtils.isNotEmpty(outCnvBaseFontSize)) {
			rootchart.append(" outCnvBaseFontSize='" + outCnvBaseFontSize + "' ");
		}
		if (StringUtils.isNotEmpty(outCnvBaseFontColor)) {
			rootchart.append(" outCnvBaseFontColor='" + outCnvBaseFontColor + "' ");
		}
		if (StringUtils.isNotEmpty(decimalPrecision)) {
			rootchart.append(" decimalPrecision='" + decimalPrecision + "' ");
		}
		if (StringUtils.isNotEmpty(chartLeftMargin)) {
			rootchart.append(" chartLeftMargin='" + chartLeftMargin + "' ");
		}
		if (StringUtils.isNotEmpty(chartRightMargin)) {
			rootchart.append(" chartRightMargin='" + chartRightMargin + "' ");
		}
		if (StringUtils.isNotEmpty(chartTopMargin)) {
			rootchart.append(" chartTopMargin='" + chartTopMargin + "' ");
		}
		if (StringUtils.isNotEmpty(chartBottomMargin)) {
			rootchart.append(" chartBottomMargin='" + chartBottomMargin + "' ");
		}
		if (StringUtils.isNotEmpty(shownames)) {
			rootchart.append(" shownames='" + shownames + "' ");
		}
		if (StringUtils.isNotEmpty(showValues)) {
			rootchart.append(" showValues='" + showValues + "' ");
		}
		if (StringUtils.isNotEmpty(animation)) {
			rootchart.append(" animation='" + animation + "' ");
		}
		if(StringUtils.isNotEmpty(PYAxisName)){
			rootchart.append(" PYAxisName='" + PYAxisName + "' ");
		}
		if(StringUtils.isNotEmpty(SYAxisName)){
			rootchart.append(" SYAxisName='" + SYAxisName + "' ");
		}
		if(StringUtils.isNotEmpty(formatNumberScale)){
			rootchart.append(" formatNumberScale='" + formatNumberScale + "' ");
		}
		if(StringUtils.isNotEmpty(rotateValues)){
			rootchart.append(" rotateValues='" + rotateValues + "' ");
		}
		if(StringUtils.isNotEmpty(divlinecolor)){
			rootchart.append(" divlinecolor='" + divlinecolor + "' ");
		}
		if(StringUtils.isNotEmpty(numdivlines)){
			rootchart.append(" numdivlines='" + numdivlines + "' ");
		}
		if(StringUtils.isNotEmpty(areaBorderColor)){
			rootchart.append(" areaBorderColor='" + areaBorderColor + "' ");
		}
		if(StringUtils.isNotEmpty(numVDivLines)){
			rootchart.append(" numVDivLines='" + numVDivLines + "' ");
		}
		if(StringUtils.isNotEmpty(vDivLineAlpha)){
			rootchart.append(" vDivLineAlpha='" + vDivLineAlpha + "' ");
		}
		if(StringUtils.isNotEmpty(rotateNames)){
			rootchart.append(" rotateNames='" + rotateNames + "' ");
		}
		if(StringUtils.isNotEmpty(anchorRadius)){
			rootchart.append(" anchorRadius='" + anchorRadius + "' ");
		}
		if(StringUtils.isNotEmpty(labelDisplay)){
			rootchart.append(" labelDisplay='" + labelDisplay + "' ");
		}
		if(StringUtils.isNotEmpty(slantLabels)){
			rootchart.append(" slantLabels='" + slantLabels + "' ");
		}
		if(StringUtils.isNotEmpty(showBorder)){
			rootchart.append(" showBorder='" + showBorder + "' ");
		}
		if(StringUtils.isNotEmpty(borderColor)){
			rootchart.append(" borderColor='" + borderColor + "' ");
		}
		return rootchart.toString();
	}

	/**
	 * 得到HttpServletRequest
	 * @return
	 */
	public HttpServletRequest getHttpServletRequest(HttpServletRequest request) {
		return (HttpServletRequest) request;
	}

	/**
	 * 返回要显示的图表文件
	 * 
	 * @return
	 */

	public abstract String drawChart(HttpServletRequest request);

	public int getWide() {
		return wide;
	}

	public void setWide(int wide) {
		this.wide = wide;
	}

	public int getHigh() {
		return high;
	}

	public void setHigh(int high) {
		this.high = high;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getSubcaption() {
		return subcaption;
	}

	public void setSubcaption(String subcaption) {
		this.subcaption = subcaption;
	}

	public String getXAxisName() {
		return xAxisName;
	}

	public void setXAxisName(String axisName) {
		xAxisName = axisName;
	}

	public String getYAxisName() {
		return yAxisName;
	}

	public void setYAxisName(String axisName) {
		yAxisName = axisName;
	}

	public String getFormatNumber() {
		return formatNumber;
	}

	public void setFormatNumber(String formatNumber) {
		this.formatNumber = formatNumber;
	}

	public String getNumberPrefix() {
		return numberPrefix;
	}

	public void setNumberPrefix(String numberPrefix) {
		this.numberPrefix = numberPrefix;
	}

	public String getNumberSuffix() {
		return numberSuffix;
	}

	public void setNumberSuffix(String numberSuffix) {
		this.numberSuffix = numberSuffix;
	}

	public String getRotateYAxisName() {
		return rotateYAxisName;
	}

	public void setRotateYAxisName(String rotateYAxisName) {
		this.rotateYAxisName = rotateYAxisName;
	}

	public String getBaseFont() {
		return baseFont;
	}

	public void setBaseFont(String baseFont) {
		this.baseFont = baseFont;
	}

	public String getBaseFontSize() {
		return baseFontSize;
	}

	public void setBaseFontSize(String baseFontSize) {
		this.baseFontSize = baseFontSize;
	}

	public String getBaseFontColor() {
		return baseFontColor;
	}

	public void setBaseFontColor(String baseFontColor) {
		this.baseFontColor = baseFontColor;
	}

	public String getBodyxml() {
		return bodyxml;
	}

	public void setBodyxml(String bodyxml) {
		this.bodyxml = bodyxml;
	}

	public String getYAxisMaxValue() {
		return yAxisMaxValue;
	}

	public void setYAxisMaxValue(String axisMaxValue) {
		yAxisMaxValue = axisMaxValue;
	}

	public String getYAxisMinValue() {
		return yAxisMinValue;
	}

	public void setYAxisMinValue(String axisMinValue) {
		yAxisMinValue = axisMinValue;
	}

	public String getOutCnvBaseFont() {
		return outCnvBaseFont;
	}

	public void setOutCnvBaseFont(String outCnvBaseFont) {
		this.outCnvBaseFont = outCnvBaseFont;
	}

	public String getOutCnvBaseFontSize() {
		return outCnvBaseFontSize;
	}

	public void setOutCnvBaseFontSize(String outCnvBaseFontSize) {
		this.outCnvBaseFontSize = outCnvBaseFontSize;
	}

	public String getOutCnvBaseFontColor() {
		return outCnvBaseFontColor;
	}

	public void setOutCnvBaseFontColor(String outCnvBaseFontColor) {
		this.outCnvBaseFontColor = outCnvBaseFontColor;
	}

	public String getDecimalPrecision() {
		return decimalPrecision;
	}

	public void setDecimalPrecision(String decimalPrecision) {
		this.decimalPrecision = decimalPrecision;
	}

	public String getChartLeftMargin() {
		return chartLeftMargin;
	}

	public void setChartLeftMargin(String chartLeftMargin) {
		this.chartLeftMargin = chartLeftMargin;
	}

	public String getChartRightMargin() {
		return chartRightMargin;
	}

	public void setChartRightMargin(String chartRightMargin) {
		this.chartRightMargin = chartRightMargin;
	}

	public String getChartTopMargin() {
		return chartTopMargin;
	}

	public void setChartTopMargin(String chartTopMargin) {
		this.chartTopMargin = chartTopMargin;
	}

	public String getChartBottomMargin() {
		return chartBottomMargin;
	}

	public void setChartBottomMargin(String chartBottomMargin) {
		this.chartBottomMargin = chartBottomMargin;
	}

	public String getShownames() {
		return shownames;
	}

	public void setShownames(String shownames) {
		this.shownames = shownames;
	}

	public String getShowValues() {
		return showValues;
	}

	public void setShowValues(String showValues) {
		this.showValues = showValues;
	}

	public String getAnimation() {
		return animation;
	}

	public void setAnimation(String animation) {
		this.animation = animation;
	}

	public String getPYAxisName() {
		return PYAxisName;
	}

	public void setPYAxisName(String axisName) {
		PYAxisName = axisName;
	}

	public String getSYAxisName() {
		return SYAxisName;
	}

	public void setSYAxisName(String axisName) {
		SYAxisName = axisName;
	}

	public String getFormatNumberScale() {
		return formatNumberScale;
	}

	public void setFormatNumberScale(String formatNumberScale) {
		this.formatNumberScale = formatNumberScale;
	}

	public String getRotateValues() {
		return rotateValues;
	}

	public void setRotateValues(String rotateValues) {
		this.rotateValues = rotateValues;
	}

	public String getDivlinecolor() {
		return divlinecolor;
	}

	public void setDivlinecolor(String divlinecolor) {
		this.divlinecolor = divlinecolor;
	}

	public String getNumdivlines() {
		return numdivlines;
	}

	public void setNumdivlines(String numdivlines) {
		this.numdivlines = numdivlines;
	}

	public String getAreaBorderColor() {
		return areaBorderColor;
	}

	public void setAreaBorderColor(String areaBorderColor) {
		this.areaBorderColor = areaBorderColor;
	}

	public String getNumVDivLines() {
		return numVDivLines;
	}

	public void setNumVDivLines(String numVDivLines) {
		this.numVDivLines = numVDivLines;
	}

	public String getVDivLineAlpha() {
		return vDivLineAlpha;
	}

	public void setVDivLineAlpha(String divLineAlpha) {
		vDivLineAlpha = divLineAlpha;
	}

	public String getRotateNames() {
		return rotateNames;
	}

	public void setRotateNames(String rotateNames) {
		this.rotateNames = rotateNames;
	}

	public String getBgColor() {
		return bgColor;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	public String getAnchorRadius() {
		return anchorRadius;
	}

	public void setAnchorRadius(String anchorRadius) {
		this.anchorRadius = anchorRadius;
	}

	public String getLabelDisplay() {
		return labelDisplay;
	}

	public void setLabelDisplay(String labelDisplay) {
		this.labelDisplay = labelDisplay;
	}

	public String getSlantLabels() {
		return slantLabels;
	}

	public void setSlantLabels(String slantLabels) {
		this.slantLabels = slantLabels;
	}

	public String getShowBorder() {
		return showBorder;
	}

	public void setShowBorder(String showBorder) {
		this.showBorder = showBorder;
	}

	public String getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}


}
