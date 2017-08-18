package com.xiangxun.epms.mobile.config.filter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xiangxun.epms.mobile.business.domain.SessionData;
import com.xiangxun.epms.mobile.business.util.Session;
import com.xiangxun.epms.mobile.util.ResponseUtil;

@Controller
public class SessionFilter extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(SessionFilter.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		Object user = Session.SESSION_MAP.get(Session.makeSessionKey(request));
		if (user == null) {
			//用户未登录。session中无值
			logger.info("This user is not logged in. session is null.");
			resultMap.put(ResponseUtil.RES_KEY_CODE, "1096");
			resultMap.put(ResponseUtil.RES_KEY_DESC, "用户未登录。");
			ResponseUtil.printWriteResponse(request.getParameter("callback"), resultMap, response);
			return false;
		}
		SessionData sd = (SessionData)user;
		String imei = request.getHeader("imei");
		if (!imei.equals(sd.getImei())) {
			//用户已在其他设备登录
			logger.info("The user has logged on to another device.");
			resultMap.put(ResponseUtil.RES_KEY_CODE, "1098");
			resultMap.put(ResponseUtil.RES_KEY_DESC, "用户已在其他设备登录。");
			ResponseUtil.printWriteResponse(request.getParameter("callback"), resultMap, response);
			return false;
		}
		String account = request.getHeader("account");
		if (!account.equals(sd.getAccount())) {
			//该用户未登录。session中用户信息与请求信息不一致
			logger.info("This user is not logged in. User information is not consistent with request information in session.");
			resultMap.put(ResponseUtil.RES_KEY_CODE, "1097");
			resultMap.put(ResponseUtil.RES_KEY_DESC, "该用户未登录。");
			ResponseUtil.printWriteResponse(request.getParameter("callback"), resultMap, response);
			return false;
		}
		//记录用户最后操作时间
		Session.LAST_OPERATION_MAP.put(Session.makeSessionKey(request), new Date());
		return true;
	}
	
}
