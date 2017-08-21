package com.xiangxun.atms.framework.compnents.fusioncharts;

import java.util.Random;
/**
 * 获取色彩的类
 * <p>Description: Color.java ：这个类是处理Color信息</p>
 * <p>Copyright:   Copyright (c) 2009</p>
 * <p>Company:     西安翔迅科技有限公司</p>
 * @author:       岩涛
 */

public class Color {
	
	public static final String nodata = "没有数据！";

	private static final String RED = "FF0033";
	private static final String GREEN = "28F064";
	private static final String BLUE = "3838E9";

	private static final String color[] = { "AFD8F8", "F6BD0F", "8BBA00",
			"FF8E46", "008E8E", "D64646", "8E468E", "588526", "B3AA00",
			"008ED6", "9D080D", "A186BE", "346DB2", "C13A36", "A02F2B",
			"7C9D35", "6A4A8D", "2B8CAC", "D2721E", "7792C1", "BF7B7A",
			"A6BC7C", "9183A7", RED, GREEN, BLUE };

	public static synchronized String GenerateColor() {

		return color[new Random().nextInt(color.length)];
	}

	public static synchronized String getRed() {
		return RED;
	}

	public static synchronized String getGreen() {
		return GREEN;
	}

	public static synchronized String getBlue() {
		return BLUE;
	}

}
