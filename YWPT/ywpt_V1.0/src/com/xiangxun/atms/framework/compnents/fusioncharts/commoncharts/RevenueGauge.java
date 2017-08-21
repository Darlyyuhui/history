package com.xiangxun.atms.framework.compnents.fusioncharts.commoncharts;

import java.util.ArrayList;
import java.util.List;

import com.xiangxun.atms.framework.compnents.fusioncharts.nodebean.Annotation;
import com.xiangxun.atms.framework.compnents.fusioncharts.nodebean.AnnotationGroup;
import com.xiangxun.atms.framework.compnents.fusioncharts.nodebean.Annotations;
import com.xiangxun.atms.framework.compnents.fusioncharts.nodebean.Chart;
import com.xiangxun.atms.framework.compnents.fusioncharts.nodebean.Color;
import com.xiangxun.atms.framework.compnents.fusioncharts.nodebean.ColorRange;
import com.xiangxun.atms.framework.compnents.fusioncharts.nodebean.Dial;
import com.xiangxun.atms.framework.compnents.fusioncharts.nodebean.Dials;


/**
 * <p>Description: 类汽车油量表图表</p>
 */
public class RevenueGauge {
	/**
	upperLimit// 最大值
	lowerLimit// 起始值

	gaugeOriginX// X在画布中坐标
	gaugeOriginY// Y在画布中坐标

	values[]// 刻度分割的大区域
	majorTMNumber// 分割的刻度数
	value// 指向的值	
	 */
	//chart 属性
	private String bgColor = "AEC0CA,FFFFFF", fillAngle = "45",
			upperLimit = "2500000", lowerLimit = "1600000",
			majorTMNumber = "10", majorTMHeight = "8", showGaugeBorder = "0",
			gaugeOuterRadius = "140", gaugeOriginX = "205",
			gaugeOriginY = "206", gaugeInnerRadius = "2",
			formatNumberScale = "1", numberPrefix = "$",
			displayValueDistance = "30", decimalPrecision = "2",
			tickMarkDecimalPrecision = "1", pivotRadius = "17",
			showPivotBorder = "1", pivotBorderColor = "000000",
			pivotBorderThickness = "5", pivotFillMix = "FFFFFF,000000";
	
	private String code[] = { "399E38", "E48739", "B41527" }; 
	private String values[] = { "1600000", "1930000", "2170000", "2500000" };
	private String value = "1900000", borderAlpha = "0", bgColor2 = "000000",
			baseWidth = "28", topWidth = "1", radius = "130";
	private String xPos = "205", yPos = "207.5";
	private String type1 = "circle", xPos1 = "0", yPos1 = "2.5",
			radius1 = "150", startAngle1 = "0", endAngle1 = "180",
			fillPattern1 = "linear", fillAsGradient1 = "1",
			fillColor1 = "dddddd,666666", fillAlpha1 = "100,100",
			fillRatio1 = "50,50", fillDegree1 = "0", showBorder1 = "1",
			borderColor1 = "444444", borderThickness1 = "2";
	private String type2 = "circle", xPos2 = "0", yPos2 = "0", radius2 = "145",
			startAngle2 = "0", endAngle2 = "180", fillPattern2 = "linear",
			fillAsGradient2 = "1", fillColor2 = "666666,ffffff",
			fillAlpha2 = "100,100", fillRatio2 = "50,50", fillDegree2 = "0";
	private Chart chart;
	private AnnotationGroup annotationGroup;
	private Dial dial;
	private Annotation annotation1;
	private Annotation annotation2;

	public RevenueGauge() {
		chart = new Chart(bgColor, fillAngle, upperLimit, lowerLimit,
				majorTMNumber, majorTMHeight, showGaugeBorder,
				gaugeOuterRadius, gaugeOriginX, gaugeOriginY, gaugeInnerRadius,
				formatNumberScale, numberPrefix, displayValueDistance,
				decimalPrecision, tickMarkDecimalPrecision, pivotRadius,
				showPivotBorder, pivotBorderColor, pivotBorderThickness,
				pivotFillMix);

		dial = new Dial(value, borderAlpha, bgColor2, baseWidth, topWidth,
				radius);
		annotationGroup = new AnnotationGroup(xPos, yPos);
		

	}

	public String RenderChart() {
		//ColorRange
		ColorRange colorRange = new ColorRange();
		List<Color> listcolor = new ArrayList<Color>();
		for (int i = 0; i < values.length - 1; i++) {
			Color color = new Color(values[i], values[i+1], code[i]);
			listcolor.add(color);
		}
		colorRange.setListcolor(listcolor);
		chart.setColorRange(colorRange);

		//Dials
		Dials dials = new Dials();
		List<Dial> listdial = new ArrayList<Dial>();
		listdial.add(dial);
		dials.setListdial(listdial);
		chart.setDials(dials);

		//Annotations
		Annotations annotations = new Annotations();
		List<Annotation> listannotation = new ArrayList<Annotation>();
		annotation1 = new Annotation(type1, xPos1, yPos1, radius1, startAngle1,
				endAngle1, fillPattern1, fillAsGradient1, fillColor1, fillAlpha1,
				fillRatio1, fillDegree1, showBorder1, borderColor1, borderThickness1);
		listannotation.add(annotation1);
		annotation2 = new Annotation(type2, xPos2, yPos2, radius2, startAngle2,
				endAngle2, fillPattern2, fillAsGradient2, fillColor2, fillAlpha2,
				fillRatio2, fillDegree2, null, null, null);
		listannotation.add(annotation2);
		annotationGroup.setListannotation(listannotation);
		annotations.setAnnotationGroup(annotationGroup);
		chart.setAnnotations(annotations);

		return chart.toString();
	}

}
