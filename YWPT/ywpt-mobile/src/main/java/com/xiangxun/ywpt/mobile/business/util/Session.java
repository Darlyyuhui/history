package com.xiangxun.ywpt.mobile.business.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.xiangxun.ywpt.mobile.business.domain.SessionData;

public class Session {

//	public static final String SESSION_KEY = "session_mobile_user_key";
	
	/**
	 * 记录登录用户map
	 */
	public static Map<String, SessionData> SESSION_MAP = new HashMap<>();
	
	/**
	 * 记录用户最后操作时间的map
	 */
	public static Map<String, Date> LAST_OPERATION_MAP = new HashMap<>();
	
	/**
	 * 生成保存在集合中的键值
	 * @param account
	 * @param imei
	 * @return
	 */
	public static String makeSessionKey(String account/*, String imei*/) {
		return account/* + "," + imei*/;
	}
	
	/**
	 * 生成保存在集合中的键值
	 * @param request
	 * @return
	 */
	public static String makeSessionKey(HttpServletRequest request) {
		String account = request.getParameter("account");
		//String imei = request.getParameter("imei");
		return makeSessionKey(account/*, imei*/);
	}
	
}
