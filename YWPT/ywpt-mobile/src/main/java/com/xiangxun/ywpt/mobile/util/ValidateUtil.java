package com.xiangxun.ywpt.mobile.util;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ValidateUtil {  

	/**
	 * 私钥
	 */
	private static final String PRIVATE_KEY = "xiangxun";
	
	/**
	 * 生成keyValue验证字符
	 * @param user
	 * @param pwd
	 * @param key 私钥
	 */
	public static String makeValidate(String user, String pwd) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		StringBuilder str = new StringBuilder();
		str.append(user);
		str.append(pwd);
		str.append(PRIVATE_KEY);
		str.append(sdf.format(new Date()));
		
		try {
			DeEncryptUtil deu = new DeEncryptUtil();
			return deu.encrypt(str.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
}
