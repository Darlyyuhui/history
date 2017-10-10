package com.xiangxun.epms.mobile.config.filter;

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
		//logger.debug("访问验证....");
		logger.debug("remote resource access validation....");
		 
		registry.addInterceptor(new ParameterFilter()).addPathPatterns("/samply/**");
		registry.addInterceptor(new SessionFilter()).addPathPatterns("/samply/server/**");
		registry.addInterceptor(new ErrorFilter()).addPathPatterns("/**");
		
		super.addInterceptors(registry);
		
	}
}
