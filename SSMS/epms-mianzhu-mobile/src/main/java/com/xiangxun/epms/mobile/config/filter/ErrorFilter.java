package com.xiangxun.epms.mobile.config.filter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xiangxun.epms.mobile.util.ResponseUtil;

/**
 * 错误拦截器
 * 
 * @author HaoXiang 2017年3月1日
 */
@Controller
public class ErrorFilter implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(ErrorFilter.class);
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
		logger.debug("请求结束。");
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		if (response.getStatus() == 500) {
			//请求出错
			logger.info("request error, code : 500");
			resultMap.put(ResponseUtil.RES_KEY_CODE, "1095");
			resultMap.put(ResponseUtil.RES_KEY_DESC, "请求处理出错。");
			ResponseUtil.printWriteResponse(request.getParameter("callback"), resultMap, response);
		} else if (response.getStatus() == 404) {
			//请求地址错误
			logger.info("request url not found, code : 404");
			resultMap.put(ResponseUtil.RES_KEY_CODE, "1094");
			resultMap.put(ResponseUtil.RES_KEY_DESC, "请求地址不存在，请确认。");
			ResponseUtil.printWriteResponse(request.getParameter("callback"), resultMap, response);
		} else if (response.getStatus() == 405) {
			//请求方式错误
			logger.info("request mothod error, code : 405");
			resultMap.put(ResponseUtil.RES_KEY_CODE, "1093");
			resultMap.put(ResponseUtil.RES_KEY_DESC, "请求方式不正确，请确认。");
			ResponseUtil.printWriteResponse(request.getParameter("callback"), resultMap, response);
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		logger.debug("请求开始...");
		return true;
	}

}
