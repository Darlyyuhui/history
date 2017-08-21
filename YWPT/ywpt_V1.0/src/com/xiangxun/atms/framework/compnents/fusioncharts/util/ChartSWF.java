package com.xiangxun.atms.framework.compnents.fusioncharts.util;

/**
 * <p>Description: 支持的图表枚举</p>
 */
public enum ChartSWF {	
	//Single-series Chart
	Column2D, //Column 2D Chart
	Column3D, //Column 3D Chart
	Pie2D, 	//Pie 2D Chart
	Pie3D,  //Pie 3D Chart
	Line,   //Line 2D Chart 
	Bar2D,  //Bar 2D Chart 
	Area2D,  //Area 2D Chart 
	Doughnut2D, //Doughnut 2D chart
	Doughnut3D, //Doughnut 3D chart
	
	//Multi-series Chart
	MSColumn2D, //Multi-series Column 2D Chart
	MSColumn3D, //Multi-series Column 3D Chart
	MSLine, //Multi-series Line Chart
	MSArea, //Multi-series Area 2D Chart
	MSBar2D, //Multi-series Bar 2D Chart 
	MSBar3D, //Multi-series Bar 3D Chart 
	
	//Stacked Chart
	StackedColumn2D, // Stacked Column 2D Chart
	StackedColumn3D, //Stacked Column 3D Chart
	StackedArea2D, //Stacked Area 2D Chart
	StackedBar2D, //Stacked Bar 2D Chart
	StackedBar3D, //Stacked Bar 3D Chart
	MSStackedColumn2D, //Multi-series Stacked Column 2D Chart
	
	//Combination Chart
	MSCombi3D, //Combination 3D (Simulated) Chart (Single Y)
	MSCombi2D, //2D (Single Y) Combination Chart
	MSColumnLine3D, //Column 3D Line (Single Y) Combination Chart
	MSCombiDY2D, //2D Dual Y Combination Chart 
	MSColumn3DLineDY, //Multi-series Column 3D Line Dual Y Combination Chart 
	StackedColumn3DLineDY, //Stacked Column 3D Line Dual Y Combination Chart
	MSStackedColumn2DLineDY, //Multi-series Stacked 2D Line Dual Y Combination Chart
	
	//XY Plot Chart
	Scatter, //Scatter (XY Plot) Chart
	Bubble,  //Bubble Chart 
	
	//Scroll Chart 
	ScrollColumn2D, //Scroll Column 2D Chart
	ScrollLine2D,  //Scroll Line 2D Chart
	ScrollArea2D,  //Scroll Area 2D Chart 
	ScrollStackedColumn2D,  //Scroll Stacked Column 2D Chart
	ScrollCombi2D,  //Scroll Combination 2D Chart 
	ScrollCombiDY2D, //Scroll Combination 2D (Dual Y) Chart
	
	//Drag-node Chart
	DragNode,
	Spline,
	MSSpline,
	
	AngularGauge;
}
