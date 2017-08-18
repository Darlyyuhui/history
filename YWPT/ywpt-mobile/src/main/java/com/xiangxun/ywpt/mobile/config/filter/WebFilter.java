package com.xiangxun.ywpt.mobile.config.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebFilter extends WebMvcConfigurerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(WebFilter.class);
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		logger.info("访问验证....");
		registry.addInterceptor(new ParameterFilter()).addPathPatterns("/**");
		registry.addInterceptor(new SessionFilter()).addPathPatterns("/server/**");
		registry.addInterceptor(new ErrorFilter()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}

}
