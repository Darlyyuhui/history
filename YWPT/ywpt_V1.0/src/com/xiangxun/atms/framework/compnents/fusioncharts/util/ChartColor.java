package com.xiangxun.atms.framework.compnents.fusioncharts.util;

public class ChartColor {
	int FC_ColorCounter = 0;

	String[] arr_FCColors = new String[] { "1941A5", "AFD8F8", "F6BD0F",
			"8BBA00", "A66EDD", "F984A1", "CCCC00", "999999", "0099CC",
			"FF0000", "006F00", "0099FF", "FF66CC", "669966", "7C7CB4",
			"FF9933", "9900FF", "99FFCC", "CCCCFF", "669900", };

	/*
	 * "1941A5"; //Dark Blue "CCCC00"; //Chrome Yellow+Green "999999"; //Grey
	 * "0099CC"; //Blue Shade "FF0000"; //Bright Red "006F00"; //Dark Green
	 * "0099FF"; //Blue (Light) "FF66CC"; //Dark Pink "669966"; //Dirty green
	 * "7C7CB4"; //Violet shade of blue "FF9933"; //Orange "9900FF"; //Violet
	 * "99FFCC";//Blue+Green Light "CCCCFF"; //Light violet "669900"; //Shade of
	 * green
	 */

	// getFCColor method helps return a color from arr_FCColors array. It uses
	// cyclic iteration to return a color from a given index. The index value is
	// maintained in FC_ColorCounter
	public String getFCColor() {
		// Update index
		FC_ColorCounter += 1;
		// Return color
		return arr_FCColors[FC_ColorCounter % arr_FCColors.length];

	}

	public static void main(String[] args) {
		ChartColor chartColor = new ChartColor();
		for (int i = 0; i < 10; i++) {
			System.out.println(chartColor.getFCColor());
		}
	}

}
