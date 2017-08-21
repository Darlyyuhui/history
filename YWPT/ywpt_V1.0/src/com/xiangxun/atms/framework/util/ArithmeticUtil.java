package com.xiangxun.atms.framework.util;

public class ArithmeticUtil {
	
	private ArithmeticUtil() {
		
	}

	public static int dividend(int dividend) {
		return dividend == 0 ? 1 : dividend;
	}

	public static long dividend(long dividend) {
		return dividend == 0 ? 1 : dividend;
	}
}
