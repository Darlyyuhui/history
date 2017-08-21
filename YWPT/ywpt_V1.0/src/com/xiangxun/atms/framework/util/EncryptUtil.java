package com.xiangxun.atms.framework.util;

/*******************************************************************************
 * author:岩涛 reviseTime:2013-03-19
 * -------------------------------------------------------------------------------
 * 本类的作用是： 明文加密
 ******************************************************************************/
public class EncryptUtil {

	public static void main(String[] args) {
		try {
			DeEncryptUtil des = new DeEncryptUtil();
			String input = "admin";
			System.out.println(input);
            System.out.println(des.encrypt(input));
            System.out.println(des.decrypt(des.encrypt(input)));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
