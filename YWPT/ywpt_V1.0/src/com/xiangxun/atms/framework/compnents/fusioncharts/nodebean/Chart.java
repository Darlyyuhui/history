package com.xiangxun.atms.framework.compnents.fusioncharts.nodebean;

import java.lang.reflect.Field;


public class Chart extends ChartNodes{
	
	private String caption;
	private String xAxisName;
	private String yAxisName;
	private String numberPrefix;
	private String numberSuffix;
	private String showValues;
	private String useRoundEdges;
	private String palette; 
	private String decimals;
	private String formatNumberScal;
	
	private String bgColor;
	private String fillAngle;
	private String upperLimit;
	private String lowerLimit;
	private String majorTMNumber;
	private String majorTMHeight;
	private String showGaugeBorder;
	private String gaugeOuterRadius;
	private String gaugeOriginX;
	private String gaugeOriginY;
	private String gaugeInnerRadius;
	private String formatNumberScale;
	//private String numberPrefix;
	private String displayValueDistance;
	private String decimalPrecision;
	private String tickMarkDecimalPrecision;
	private String pivotRadius;
	private String showPivotBorder;
	private String pivotBorderColor;
	private String pivotBorderThickness;
	private String pivotFillMix;
	
	private String rotateYAxisName; // 0/1 控制Y轴标题的旋转模式
	private String yAxisNameWidth; //如果Y轴标题为横向显示，控制它的Width，(In Pixels) 
	
	private String rotateXAxisName; // 0/1 控制X轴标题的旋转模式(Bar图)
	private String xAxisNameWidth; //如果X轴标题为横向显示，控制它的Width，(In Pixels) 
	private String showLabels; //Bar图是否竖轴（x）的标题
	
	private String numVisiblePlot; //滚动图的可见区域内的图形数，0表示显示所有
	
	private String showPercentValues; //Pie图是否显示百分比
	
	private String yAxisMinValue;
	private String yAxisMaxValue; 
	
	private String subCaption; 
	
	
	
	
	public Chart() {
		
	}

	public Chart(String bgColor, String fillAngle, String upperLimit,
			String lowerLimit, String majorTMNumber, String majorTMHeight,
			String showGaugeBorder, String gaugeOuterRadius,
			String gaugeOriginX, String gaugeOriginY, String gaugeInnerRadius,
			String formatNumberScale, String numberPrefix,
			String displayValueDistance, String decimalPrecision,
			String tickMarkDecimalPrecision, String pivotRadius,
			String showPivotBorder, String pivotBorderColor,
			String pivotBorderThickness, String pivotFillMix) {
		this.bgColor = bgColor;
		this.fillAngle = fillAngle;
		this.upperLimit = upperLimit;
		this.lowerLimit = lowerLimit;
		this.majorTMNumber = majorTMNumber;
		this.majorTMHeight = majorTMHeight;
		this.showGaugeBorder = showGaugeBorder;
		this.gaugeOuterRadius = gaugeOuterRadius;
		this.gaugeOriginX = gaugeOriginX;
		this.gaugeOriginY = gaugeOriginY;
		this.gaugeInnerRadius = gaugeInnerRadius;
		this.formatNumberScale = formatNumberScale;
		this.numberPrefix = numberPrefix;
		this.displayValueDistance = displayValueDistance;
		this.decimalPrecision = decimalPrecision;
		this.tickMarkDecimalPrecision = tickMarkDecimalPrecision;
		this.pivotRadius = pivotRadius;
		this.showPivotBorder = showPivotBorder;
		this.pivotBorderColor = pivotBorderColor;
		this.pivotBorderThickness = pivotBorderThickness;
		this.pivotFillMix = pivotFillMix;
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
	 * @return the palette
	 */
	public String getPalette() {
		return palette;
	}



	/**
	 * @param palette the palette to set
	 */
	public void setPalette(String palette) {
		this.palette = palette;
	}



	/**
	 * @return the decimals
	 */
	public String getDecimals() {
		return decimals;
	}



	/**
	 * @param decimals the decimals to set
	 */
	public void setDecimals(String decimals) {
		this.decimals = decimals;
	}



	/**
	 * @return the formatNumberScal
	 */
	public String getFormatNumberScal() {
		return formatNumberScal;
	}



	/**
	 * @param formatNumberScal the formatNumberScal to set
	 */
	public void setFormatNumberScal(String formatNumberScal) {
		this.formatNumberScal = formatNumberScal;
	}



	/**
	 * @return the bgColor
	 */
	public String getBgColor() {
		return bgColor;
	}



	/**
	 * @param bgColor the bgColor to set
	 */
	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}



	/**
	 * @return the fillAngle
	 */
	public String getFillAngle() {
		return fillAngle;
	}



	/**
	 * @param fillAngle the fillAngle to set
	 */
	public void setFillAngle(String fillAngle) {
		this.fillAngle = fillAngle;
	}



	/**
	 * @return the upperLimit
	 */
	public String getUpperLimit() {
		return upperLimit;
	}



	/**
	 * @param upperLimit the upperLimit to set
	 */
	public void setUpperLimit(String upperLimit) {
		this.upperLimit = upperLimit;
	}



	/**
	 * @return the lowerLimit
	 */
	public String getLowerLimit() {
		return lowerLimit;
	}



	/**
	 * @param lowerLimit the lowerLimit to set
	 */
	public void setLowerLimit(String lowerLimit) {
		this.lowerLimit = lowerLimit;
	}



	/**
	 * @return the majorTMNumber
	 */
	public String getMajorTMNumber() {
		return majorTMNumber;
	}



	/**
	 * @param majorTMNumber the majorTMNumber to set
	 */
	public void setMajorTMNumber(String majorTMNumber) {
		this.majorTMNumber = majorTMNumber;
	}



	/**
	 * @return the majorTMHeight
	 */
	public String getMajorTMHeight() {
		return majorTMHeight;
	}



	/**
	 * @param majorTMHeight the majorTMHeight to set
	 */
	public void setMajorTMHeight(String majorTMHeight) {
		this.majorTMHeight = majorTMHeight;
	}



	/**
	 * @return the showGaugeBorder
	 */
	public String getShowGaugeBorder() {
		return showGaugeBorder;
	}



	/**
	 * @param showGaugeBorder the showGaugeBorder to set
	 */
	public void setShowGaugeBorder(String showGaugeBorder) {
		this.showGaugeBorder = showGaugeBorder;
	}



	/**
	 * @return the gaugeOuterRadius
	 */
	public String getGaugeOuterRadius() {
		return gaugeOuterRadius;
	}



	/**
	 * @param gaugeOuterRadius the gaugeOuterRadius to set
	 */
	public void setGaugeOuterRadius(String gaugeOuterRadius) {
		this.gaugeOuterRadius = gaugeOuterRadius;
	}



	/**
	 * @return the gaugeOriginX
	 */
	public String getGaugeOriginX() {
		return gaugeOriginX;
	}



	/**
	 * @param gaugeOriginX the gaugeOriginX to set
	 */
	public void setGaugeOriginX(String gaugeOriginX) {
		this.gaugeOriginX = gaugeOriginX;
	}



	/**
	 * @return the gaugeOriginY
	 */
	public String getGaugeOriginY() {
		return gaugeOriginY;
	}



	/**
	 * @param gaugeOriginY the gaugeOriginY to set
	 */
	public void setGaugeOriginY(String gaugeOriginY) {
		this.gaugeOriginY = gaugeOriginY;
	}



	/**
	 * @return the gaugeInnerRadius
	 */
	public String getGaugeInnerRadius() {
		return gaugeInnerRadius;
	}



	/**
	 * @param gaugeInnerRadius the gaugeInnerRadius to set
	 */
	public void setGaugeInnerRadius(String gaugeInnerRadius) {
		this.gaugeInnerRadius = gaugeInnerRadius;
	}



	/**
	 * @return the formatNumberScale
	 */
	public String getFormatNumberScale() {
		return formatNumberScale;
	}



	/**
	 * @param formatNumberScale the formatNumberScale to set
	 */
	public void setFormatNumberScale(String formatNumberScale) {
		this.formatNumberScale = formatNumberScale;
	}



	/**
	 * @return the displayValueDistance
	 */
	public String getDisplayValueDistance() {
		return displayValueDistance;
	}



	/**
	 * @param displayValueDistance the displayValueDistance to set
	 */
	public void setDisplayValueDistance(String displayValueDistance) {
		this.displayValueDistance = displayValueDistance;
	}



	/**
	 * @return the decimalPrecision
	 */
	public String getDecimalPrecision() {
		return decimalPrecision;
	}



	/**
	 * @param decimalPrecision the decimalPrecision to set
	 */
	public void setDecimalPrecision(String decimalPrecision) {
		this.decimalPrecision = decimalPrecision;
	}



	/**
	 * @return the tickMarkDecimalPrecision
	 */
	public String getTickMarkDecimalPrecision() {
		return tickMarkDecimalPrecision;
	}



	/**
	 * @param tickMarkDecimalPrecision the tickMarkDecimalPrecision to set
	 */
	public void setTickMarkDecimalPrecision(String tickMarkDecimalPrecision) {
		this.tickMarkDecimalPrecision = tickMarkDecimalPrecision;
	}



	/**
	 * @return the pivotRadius
	 */
	public String getPivotRadius() {
		return pivotRadius;
	}



	/**
	 * @param pivotRadius the pivotRadius to set
	 */
	public void setPivotRadius(String pivotRadius) {
		this.pivotRadius = pivotRadius;
	}



	/**
	 * @return the showPivotBorder
	 */
	public String getShowPivotBorder() {
		return showPivotBorder;
	}



	/**
	 * @param showPivotBorder the showPivotBorder to set
	 */
	public void setShowPivotBorder(String showPivotBorder) {
		this.showPivotBorder = showPivotBorder;
	}



	/**
	 * @return the pivotBorderColor
	 */
	public String getPivotBorderColor() {
		return pivotBorderColor;
	}



	/**
	 * @param pivotBorderColor the pivotBorderColor to set
	 */
	public void setPivotBorderColor(String pivotBorderColor) {
		this.pivotBorderColor = pivotBorderColor;
	}



	/**
	 * @return the pivotBorderThickness
	 */
	public String getPivotBorderThickness() {
		return pivotBorderThickness;
	}



	/**
	 * @param pivotBorderThickness the pivotBorderThickness to set
	 */
	public void setPivotBorderThickness(String pivotBorderThickness) {
		this.pivotBorderThickness = pivotBorderThickness;
	}



	/**
	 * @return the pivotFillMix
	 */
	public String getPivotFillMix() {
		return pivotFillMix;
	}



	/**
	 * @param pivotFillMix the pivotFillMix to set
	 */
	public void setPivotFillMix(String pivotFillMix) {
		this.pivotFillMix = pivotFillMix;
	}
	
	



	/**
	 * @return the rotateYAxisName
	 */
	public String getRotateYAxisName() {
		return rotateYAxisName;
	}

	/**
	 * @param rotateYAxisName the rotateYAxisName to set
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
	 * @param axisNameWidth the yAxisNameWidth to set
	 */
	public void setYAxisNameWidth(String axisNameWidth) {
		yAxisNameWidth = axisNameWidth;
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
	 * @return the numVisiblePlot
	 */
	public String getNumVisiblePlot() {
		return numVisiblePlot;
	}

	/**
	 * @param numVisiblePlot the numVisiblePlot to set
	 */
	public void setNumVisiblePlot(String numVisiblePlot) {
		this.numVisiblePlot = numVisiblePlot;
	}
	

	/**
	 * @return the showPercentValues
	 */
	public String getShowPercentValues() {
		return showPercentValues;
	}

	/**
	 * @param showPercentValues the showPercentValues to set
	 */
	public void setShowPercentValues(String showPercentValues) {
		this.showPercentValues = showPercentValues;
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<chart ");
		Field[] fs =getClass().getDeclaredFields();
		for(Field f:fs){
			try {
				if (f.get(this) != null) {
					sb.append(f.getName() + "='" + f.get(this)+"' ");
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}		
		sb.append(">");		
		if(getListset()!=null){
			for(Set s:getListset()){
				sb.append(s.toString());
			}
		}
		
		if(getCategories()!=null){
			sb.append(getCategories().toString());
		}
		
		if(getListdateset()!=null){
			for(Dataset d:getListdateset()){
				sb.append(d.toString());
			}
		}
		
		if(getTrendLines()!=null){
			sb.append(getTrendLines().toString());
		}
		if(getStyles()!=null){
			sb.append(getStyles().toString());
		}		
		if(getColorRange()!=null){
			sb.append(getColorRange().toString());
		}
		if(getDials()!=null){
			sb.append(getDials().toString());
		}
		if(getAnnotations()!=null){
			sb.append(getAnnotations().toString());
		}
		
		sb.append("</chart>");
		return sb.toString();
	}
	
	
	
	
}
