package com.xiangxun.atms.framework.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.common.net.HttpHeaders;
import com.xiangxun.atms.framework.security.OperatorDetails;
import com.xiangxun.atms.framework.security.SpringSecurityUtils;

/***
 * 登陆SESSION系统拦截器
 * @author zhouhaij
 * @May 6, 2013 2:30:06 PM
 */
public class SessionCheckInterceptor extends HandlerInterceptorAdapter {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 后台session控制
		String[] noFilters = new String[] { "index.jsp", "login.jsp","login",".css",".js",".png",".jpg",".gif",".swf"};
		String uri = request.getRequestURI();
		
		boolean beFilter = true;
		for (String s : noFilters) {
			if (uri.indexOf(s) != -1) {
				beFilter = false;
				break;
			}
		}
		
		//处理flash请求session丢失的问题，如果是来自相同域名的flash请求则直接响应
		if (request.getHeader("User-Agent").equals("Shockwave Flash")) 
		{
			beFilter = false;
		}
		
		if (beFilter) {
			OperatorDetails userInfo = SpringSecurityUtils.getCurrentUser();
			if (userInfo == null) {
				// Http 1.0 header, set a fix expires date.
				response.setDateHeader(HttpHeaders.EXPIRES, System.currentTimeMillis()-1000);
				// Http 1.1 header, set a time after now.
				response.setHeader(HttpHeaders.CACHE_CONTROL, "private, max-age="+0);
				//重定向到登陆页面
				response.sendRedirect(request.getContextPath().concat("/index.jsp"));
				return false;
			}
		}
		
		return super.preHandle(request, response, handler);
	}
}
