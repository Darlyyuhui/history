package com.xiangxun.epms.mobile.config.filter;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xiangxun.epms.mobile.util.CRC16Validator;
import com.xiangxun.epms.mobile.util.ResponseUtil;

/**
 * 参数验证过滤器
 * @author HaoXiang
 * 2017年3月1日
 */
@Controller
public class ParameterFilter extends HandlerInterceptorAdapter  {

	private static final Logger logger = LoggerFactory.getLogger(ParameterFilter.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		Map<String, String> map = this.getheaderMap(request);
		
		
		if (!map.containsKey("account") || !map.containsKey("crc") || !map.containsKey("imei")) {
			//请求地址缺少关键参数
			logger.info("The requested address is missing key parameters.");
			resultMap.put(ResponseUtil.RES_KEY_CODE, "1090");
			resultMap.put(ResponseUtil.RES_KEY_DESC, "请求地址缺少关键参数。");
			ResponseUtil.printWriteResponse(request.getParameter("callback"), resultMap, response);
			return false;
		}
		if (!validateByCRC(request)) {
			//CRC校验失败
			logger.info("CRC code validate error.");
			resultMap.put(ResponseUtil.RES_KEY_CODE, "1091");
			resultMap.put(ResponseUtil.RES_KEY_DESC, "CRC校验码验证失败。");
			ResponseUtil.printWriteResponse(request.getParameter("callback"), resultMap, response);
			return false;
		}
		return true;
	}
	
	/**
	 * 校验CRC是否正确
	 * @param request
	 * @return
	 */
	private boolean validateByCRC(HttpServletRequest request) {
		StringBuilder str = new StringBuilder();
		String template = "%s=%s";
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Map<String, String> resultMap=this.getheaderMap(request);
		Map<String, String[]> map =this.mapChange(resultMap) ;
		//Enumeration<String> params = request.getParameterNames();
		String key = "";
		String crc = "";
		for(String pa : resultMap.keySet()) {
			key = pa;
			if ("crc".equals(key.toLowerCase())) {
				crc = request.getHeader(key);
				continue;
			}
			str.append(String.format(template, key, getStrByArray(map.get(key))));
			str.append("&");
		}
		String parms = "";
		if (str.length() > 0) {
			parms = str.substring(0, str.length()-1);
		}
		return CRC16Validator.isValid(parms, crc);
	}
	
	/**
	 * 拼接属性值
	 * @param strs
	 * @return
	 */
	private String getStrByArray(String[] strs) {
		StringBuilder str = new StringBuilder();
		for (String s : strs) {
			str.append(s);
			str.append(",");
		}
		if (str.length() > 0) {
			return str.substring(0, str.length()-1);
		}
		return "";
	}
	private Map<String, String> getheaderMap(HttpServletRequest request){
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		String account =request.getHeader("account");
		String crc= request.getHeader("crc");
		String imei=request.getHeader("imei");
		String pwd= request.getHeader("pwd");
		if(crc!=null){
			resultMap.put("crc", crc);
		}
		if(imei!=null){
			resultMap.put("imei", imei);
		}
		if(pwd!=null){
			resultMap.put("pwd", pwd);
		}
		if(account!=null){
			resultMap.put("account", account);
		}
		return resultMap;
	}
	private Map<String, String[]> mapChange(Map<String, String> map){
		Map<String, String[]> resultMap = new HashMap<String, String[]>();
		for(String key:map.keySet() ){
			String[] ss=new String[1];
			ss[0] = map.get(key);
			resultMap.put(key, ss);
		}
		return resultMap;
	}
}
