package com.xiangxun.atms.framework.util;

public class CarPlateNumUtil {
	
	
	public static String makeQueryCarNum(String userCarNum) {
		//剔除用户输入的sql通配符，防止影响SQL正常工作
		if (userCarNum.indexOf("_") > -1) {
			userCarNum = userCarNum.replace("_", "");
		}
		if (userCarNum.indexOf("%") > -1) {
			userCarNum = userCarNum.replace("%", "");
		}
		StringBuilder carNum = new StringBuilder();
		int length = userCarNum.length();
		//通配符，7位=_ 否则=%
		String tpf = length == 7 ? "_" : "%";
		if (userCarNum.indexOf("*") > -1) {
			char[] cs = userCarNum.toCharArray();
			String s;
			for (char c : cs) {
				s = Character.toString(c);
				if ("*".equals(s)) {
					carNum.append(tpf);
				}else{
					carNum.append(s);
				}
			}
		}else{
			if(length>=7){
				carNum.append(userCarNum);
			}else{
				carNum.append("%");
				carNum.append(userCarNum);
				carNum.append("%");
			}
		}
		return carNum.toString().trim().toUpperCase();
	}

}
