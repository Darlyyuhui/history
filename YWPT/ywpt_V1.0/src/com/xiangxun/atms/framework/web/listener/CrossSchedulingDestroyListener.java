package com.xiangxun.atms.framework.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import com.xiangxun.atms.framework.base.ApplicationContextHolder;


/**
 * 卡口定时器销毁监听类
 */
public class CrossSchedulingDestroyListener implements ServletContextListener {
//	private static final Logging logger = new Logging(CrossSchedulingDestroyListener.class);
	
    public void contextDestroyed(ServletContextEvent sce) {
    	Scheduler  scheduler= (Scheduler) ApplicationContextHolder.getBean("crossScheduling");
    	if(scheduler!=null){
    		 try {
				scheduler.shutdown(true);
				Thread.sleep(1000);

			} catch (SchedulerException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

    	}
    }

    public void contextInitialized(ServletContextEvent sce) {

    }

   
	
}
