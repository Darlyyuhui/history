/**
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.xiangxun.atms.framework.util;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.servlet.ServletRequest;

import org.springframework.util.Assert;



/**
 * Http与Servlet工具类.
 * 
 */
public class Servlets {

	/**
	 * 取得带相同前缀的Request Parameters, copy from spring WebUtils.
	 * 
	 * 返回的结果的Parameter名已去除前缀.
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, Object> getParametersStartingWith(ServletRequest request, String prefix) {
		Assert.notNull(request, "Request must not be null");
		Enumeration paramNames = request.getParameterNames();
		Map<String, Object> params = new TreeMap<String, Object>();
		if (prefix == null) {
			prefix = "";
		}
		while (paramNames != null && paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			if ("".equals(prefix) || paramName.startsWith(prefix)) {
				String unprefixed = paramName.substring(prefix.length());
				String[] values = request.getParameterValues(paramName);
				for (String string : values) {
						//对获取的值进行转码
						string = StringUtils.getUTF8(string);
				}
				if (values == null || values.length == 0) {
					// Do nothing, no values found at all.
				} else if (values.length > 1) {
					params.put(unprefixed, values);
				} else {
					//岩涛 05.20修改 当传参的值为空字符串，则不放入MAP
					if(!"".equals(values[0])){
						params.put(unprefixed, values[0].trim());
					}
				}
			}
		}
		return params;
	}

	/**
	 * 组合Parameters生成Query String的Parameter部分, 并在paramter name上加上prefix.
	 * 
	 * @see #getParametersStartingWith
	 */
	public static String encodeParameterStringWithPrefix(Map<String, Object> params, String prefix) {
		if (params == null || params.size() == 0) {
			return "";
		}
		if (prefix == null) {
			prefix = "";
		}
		StringBuilder queryStringBuilder = new StringBuilder();
		try {
			Iterator<Entry<String, Object>> it = params.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, Object> entry = it.next();
				if(entry.getValue()==null){
					continue;
				}
				queryStringBuilder.append(prefix).append(entry.getKey()).append('=').append(java.net.URLEncoder.encode(entry.getValue().toString(),"utf-8"));
				if (it.hasNext()) {
					queryStringBuilder.append('&');
				}
			}
		} catch (UnsupportedEncodingException e) {
		}
		return queryStringBuilder.toString();
	}
	
	/**
	 * 组合Parameters生成Query String的Parameter部分, 并在paramter name上加上prefix. 支持POST方式的提交  YanTao ADD
	 * 
	 * @see #getParametersStartingWith
	 */
	public static String encodeParameterStringWithPrefixByPost(Map<String, Object> params, String prefix) {
		if (params == null || params.size() == 0) {
			return "";
		}
		if (prefix == null) {
			prefix = "";
		}
		StringBuilder queryStringBuilder = new StringBuilder();
		try {
			Iterator<Entry<String, Object>> it = params.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, Object> entry = it.next();
				if(entry.getValue()==null){
					continue;
				}
				String valuestr = entry.getValue().toString();
				String valuetemp = StringUtils.removets(valuestr);
				queryStringBuilder.append(prefix).append(entry.getKey()).append('=').append(valuetemp);
				if (it.hasNext()) {
					queryStringBuilder.append('&');
				}
			}
		} catch (Exception e) {
		}
		return queryStringBuilder.toString();
	}
	
	

}
