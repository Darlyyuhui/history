package com.xiangxun.atms.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 单元请求执行时间过滤器
 * @author YanTao
 * @date 2013/02/27
 */
public class ProcessTimeFilter implements Filter {
	protected final Logger log = LoggerFactory.getLogger(ProcessTimeFilter.class);
	
	public static final String START_TIME = "_start_time";

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		long time = System.currentTimeMillis();
		chain.doFilter(request, response);
		time = System.currentTimeMillis() - time;
		log.debug("请求： "+request.getRequestURI()+" 执行时间： "+time+" ms");
	}

	public void init(FilterConfig arg0) throws ServletException {
	}
}
