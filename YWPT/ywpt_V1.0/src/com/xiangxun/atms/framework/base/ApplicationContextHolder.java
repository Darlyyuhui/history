package com.xiangxun.atms.framework.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.beans.factory.xml.ResourceEntityResolver;

/**
 * 用于持有spring的applicationContext,一个系统只能有一个ApplicationContextHolder 
 * 
 * 
 * <br />
 * <pre>
 * 使用方法:
 * &lt;bean class="org.basic.tools.util.ApplicationContextHolder"/>
 * 
 * 在java代码中则可以如此使用: 
 * BlogDao dao = (BlogDao)ApplicationContextHolder.getBean("blogDao");
 * </pre>
 * @author by google Version 1.0
 */
public class ApplicationContextHolder implements ApplicationContextAware{
	
	private static Log log = LogFactory.getLog(ApplicationContextHolder.class);
	
	private static ApplicationContext applicationContext;
	
	@SuppressWarnings("static-access")
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		if(this.applicationContext != null) {
			log.info("ApplicationContextHolder already holded 'applicationContext'.");
		}else{
			this.applicationContext = context;
		}
		log.info("holded applicationContext,displayName:"+applicationContext.getDisplayName());
	}
	
	public static ApplicationContext getApplicationContext() {
		if(applicationContext == null)
			throw new IllegalStateException("'applicationContext' property is null,ApplicationContextHolder not yet init.");
		return applicationContext;
	}
	
	public static Object getBean(String beanName) {
		return getApplicationContext().getBean(beanName);
	}
	
	public static  <T> T getBean(Class<T> bean){
		return getApplicationContext().getBean(bean);
	}

	public static void loadBean(String[] configLocations){
		ConfigurableApplicationContext configurableApplicationContext =(ConfigurableApplicationContext)applicationContext;
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader((BeanDefinitionRegistry)configurableApplicationContext.getBeanFactory());
		xmlBeanDefinitionReader.setResourceLoader(configurableApplicationContext);
		xmlBeanDefinitionReader.setEntityResolver(new ResourceEntityResolver(configurableApplicationContext));
		try{
			for (String string : configLocations) {
				xmlBeanDefinitionReader.loadBeanDefinitions(configurableApplicationContext.getResource(string));
			}
			configurableApplicationContext.refresh();
		}catch(BeansException e){
			log.error(e);
		}
	}
	
}
