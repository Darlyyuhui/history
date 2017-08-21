package com.xiangxun.atms.framework.cache;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
/**
 * <p>类说明：缓存组件监听器</p>
 * @version 1.0
 */
public class CacheListener implements ServletContextListener{
	/**
	 * 缓存组件工厂
	 */
	private CacheFactory cacheFactory;
	/**
	 * web.xml 加载到此监听器时 执行此方法
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			doListener(arg0.getServletContext());
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * <p>方法说明：执行初始化工程</p>
	 * @param servletContext
	 * @throws ServletException
	 */
	public void doListener(ServletContext servletContext) throws ServletException {
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        //判断是否缓存过数据，如果没有则需要缓存数据
        this.setCacheFactory((CacheFactory) wac.getBean("cacheFactory"));
        if(!this.getCacheFactory().isCacheInitialized()){
        	this.getCacheFactory().initCache();
        }
        
    }
	
	

	/**
	 * 销毁
	 */
	public void contextDestroyed(ServletContextEvent arg0) {

	}
	//~setter and getter===============================================================

	/**
	 * @return the cacheFactory
	 */
	public CacheFactory getCacheFactory() {
		return cacheFactory;
	}

	/**
	 * @param cacheFactory the cacheFactory to set
	 */
	public void setCacheFactory(CacheFactory cacheFactory) {
		this.cacheFactory = cacheFactory;
	}
	
}
