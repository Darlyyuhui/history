package com.xiangxun.ywpt.mobile.config.filter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xiangxun.ywpt.mobile.business.domain.SessionData;
import com.xiangxun.ywpt.mobile.business.util.Session;
import com.xiangxun.ywpt.mobile.util.ResponseUtil;

@Controller
public class SessionFilter extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(SessionFilter.class);

	/*@Value("${static.type}")
	private String types;*/
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		/*String[] type = types.split(",");
		String uri = request.getRequestURI();
		for(String str : type){
			if(uri.endsWith(str)){
				return true;
			}
		}*/
		
		Map<String, Object> resultMap = new HashMap<>();
		Object user = Session.SESSION_MAP.get(Session.makeSessionKey(request));
		if (user == null) {
			logger.info("用户未登录。session中无值");
			resultMap.put(ResponseUtil.RES_KEY_CODE, "0");
			resultMap.put(ResponseUtil.RES_KEY_DESC, "用户未登录。");
			ResponseUtil.printWriteResponse(request.getParameter("callback"), resultMap, response);
			return false;
		}
		SessionData sd = (SessionData)user;
		/*String imei = request.getParameter("imei");
		if (!imei.equals(sd.getImei())) {
			logger.info("登录用户设备不一致。");
			resultMap.put(ResponseUtil.RES_KEY_CODE, "1098");
			resultMap.put(ResponseUtil.RES_KEY_DESC, "用户设备非法。");
			ResponseUtil.printWriteResponse(request.getParameter("callback"), resultMap, response);
			return false;
		}*/
		//String account = request.getParameter("loginName");
		String account = request.getHeader("loginName");;
		
		if (!account.equals(sd.getAccount())) {
			logger.info("该用户未登录。session中用户信息与请求信息不一致");
			resultMap.put(ResponseUtil.RES_KEY_CODE, "0");
			resultMap.put(ResponseUtil.RES_KEY_DESC, "该用户未登录。");
			ResponseUtil.printWriteResponse(request.getParameter("callback"), resultMap, response);
			return false;
		}
		//记录用户最后操作时间
		Session.LAST_OPERATION_MAP.put(Session.makeSessionKey(request), new Date());
		return true;
	}
	
}
