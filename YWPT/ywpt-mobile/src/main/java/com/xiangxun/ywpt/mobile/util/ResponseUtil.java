package com.xiangxun.ywpt.mobile.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {

	/**
	 * 返回的结果或结果集存放的键值
	 */
	public static final String RES_KEY_RESULT = "data";
	/**
	 * 返回的状态编号存放的键值
	 */
	public static final String RES_KEY_CODE = "status";
	/**
	 * 返回的状态描述存放的键值
	 */
	public static final String RES_KEY_DESC = "message";
	
	public static final String RES_KEY_PAGESIZE = "pageSize";
	
	public static final String RES_KEY_PAGE = "pageNo";
	
	public static final String RES_KEY_TOTALSIZE = "totalSize";
	
	public static final String RES_KEY_TOTALPAGE = "totalPage";
	
	@Deprecated
	public static void writeResponse(String jsonpStr, Object obj, HttpServletResponse response) {
		response.setContentType("text/json;charset=UTF-8");
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			String str = JsonUtil.toJson(obj);
			if (jsonpStr != null && !"".equals(jsonpStr)) {
				str = jsonpStr + "(" + str + ")";
			}
			os.write(str.getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.flush();
					os.close();
				}
			} catch (IOException e) {
				os = null;
			}
		}
	}
	
	public static void printWriteResponse(String jsonpStr, Object obj, HttpServletResponse response) {
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			String str = JsonUtil.toJson(obj);
			if (jsonpStr != null && !"".equals(jsonpStr)) {
				str = jsonpStr + "(" + str + ")";
			}
			out.write(str);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}
	
}
