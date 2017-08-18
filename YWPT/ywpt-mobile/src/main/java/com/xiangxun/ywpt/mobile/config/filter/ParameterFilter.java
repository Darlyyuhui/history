package com.xiangxun.ywpt.mobile.config.filter;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xiangxun.ywpt.mobile.util.CRC16Validator;
//import com.xiangxun.ywpt.mobile.util.ResponseUtil;

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
		
		/*Map<String, Object> resultMap = new HashMap<>();
		
		Map<String, String[]> map = request.getParameterMap();
		if (!map.containsKey("callback") || !map.containsKey("crc") || !map.containsKey("imei")) {
			logger.info("请求地址缺少关键参数。");
			resultMap.put(ResponseUtil.RES_KEY_CODE, "1090");
			resultMap.put(ResponseUtil.RES_KEY_DESC, "请求地址缺少关键参数呜呜呜呜。");
			ResponseUtil.printWriteResponse(request.getParameter("callback"), resultMap, response);
			return false;
		}*/
//		if (!validateByCRC(request)) {
//			logger.info("CRC校验码验证失败。");
//			resultMap.put(ResponseUtil.RES_KEY_CODE, "1091");
//			resultMap.put(ResponseUtil.RES_KEY_DESC, "CRC校验码验证失败。");
//			ResponseUtil.printWriteResponse(request.getParameter("callback"), resultMap, response);
//			return false;
//		}
		
		return true;
	}
	
	/**
	 * 校验CRC是否正确
	 * @param request
	 * @return
	 */
	/*private boolean validateByCRC(HttpServletRequest request) {
		StringBuilder str = new StringBuilder();
		String template = "%s=%s";
		
		Map<String, String[]> map = request.getParameterMap();
		Enumeration<String> params = request.getParameterNames();
		
		String key = "";
		String crc = "";
		while(params.hasMoreElements()) {
			key = params.nextElement();
			if ("crc".equals(key.toLowerCase())) {
				crc = request.getParameter(key);
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
	*/
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
	
}
