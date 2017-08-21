package com.xiangxun.atms.framework.web.listener;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xiangxun.atms.common.system.service.ParamsService;
import com.xiangxun.atms.common.system.vo.SystemParams;
import com.xiangxun.atms.framework.constant.Constant;
import com.xiangxun.atms.framework.log.Logging;
import com.xiangxun.atms.framework.monitor.MonitorMain;


/**
 * 系统启动监听器
 */
public class SystemInitListener implements ServletContextListener {
	private static final Logging logger = new Logging(SystemInitListener.class);
	
    public void contextDestroyed(ServletContextEvent sce) {

    }

	public void contextInitialized(ServletContextEvent sce) {
    	//获取产品发布模式
    	String runMode = sce.getServletContext().getInitParameter("run.mode");
    	
    	String jmsURL = sce.getServletContext().getInitParameter("org.apache.activemq.brokerURL");
    	
    	if(Constant.RELEASE_MODE.equals(runMode)){
    		logger.debugLine();
    	    logger.info("********************************************");
    	    logger.info("系统检测到本系统运行模式为：发布模式");
    		sce.getServletContext().setAttribute(Constant.APPLICATION_CONTEXT_MODE,runMode);
    	}else{
    		logger.warn("系统检测到本系统运行模式为：开发调试模式");
    	}
    	if(StringUtils.isNotBlank(jmsURL)){
    		logger.info("消息服务器的地址为:"+jmsURL);
    		System.setProperty("atms.org.apache.activemq.brokerURL",jmsURL);
    	}
        systemStartup(sce.getServletContext());

        MonitorMain.init();

    }

    /**
     * 应用平台启动 
     */
	private void systemStartup(ServletContext servletContext) {
        // 用户对应用初始化的一些处理，需要可以加。
        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        
        logger.debugLine();
        logger.info("********************************************");
        logger.info("系统正在启动...");
        logger.info("正在加载系统参数...");
        ParamsService paramServcie = applicationContext.getBean(ParamsService.class);
        Map<String,String> maps = paramServcie.getAllParams("1");
        ParamsService.SYSTEM_PARAMS.putAll(maps);
        
        //存入用户参数
        Map<String,String> userMaps = paramServcie.getAllParams("0");
        ParamsService.SYSTEM_PARAMS.putAll(userMaps);
        
        maps.putAll(userMaps);        
        //存入application变量，便于页面使用
        servletContext.setAttribute("atms",maps);
        
        //将地图引擎配置信息存入application变量中，以供切换地图时用。add by kouyunhao 2014-02-13
        SystemParams params = paramServcie.getParamsByKeyAndType("mapEngineType", "1");
        if(null == params) {
        	// 如果系统参数中没有设置地图引擎参数，这里进行添加，防止空指针异常   xiongjie添加
        	params = new SystemParams();
        	params.setMemo("地图引擎设置。 0：ARCGIS 1：PIGIS 2：开源地图。");
        	params.setName("mapEngineType");
        	params.setTypes("1");
        	params.setValue("2");
        	paramServcie.save(params);
        }
        servletContext.setAttribute("mapEngineType",params.getValue());
        //设置web应用路径
        servletContext.setAttribute("contextPath", servletContext.getContextPath());
        System.setProperty("deployname",servletContext.getContextPath());
        String path = servletContext.getRealPath("/");
        //设置应用部署的路径
        System.setProperty(Constant.CONTEXT_REALPATH, path);
        
        String webroot = System.getProperty("atms.root");
        if(StringUtils.isBlank(webroot)){
        	System.setProperty("atms.root",path);
        }
        
        logger.info("系统参数加载成功...");
        logger.info("********************************************");
        logger.info("系统启动成功.....");
        logger.debugLine();
    }
	
}
