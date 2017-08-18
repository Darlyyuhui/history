package com.xiangxun.epms.mobile.business.job;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xiangxun.epms.mobile.business.domain.SessionData;
import com.xiangxun.epms.mobile.business.util.Session;
import com.xiangxun.epms.mobile.config.parameter.CustomParameters;

@Component
@Configurable
@EnableScheduling
public class SessionJob {

	private static final Logger logger = LoggerFactory.getLogger(SessionJob.class);
	
	@Resource
	CustomParameters custom;
	
	// 每15秒执行一次
	@Scheduled(cron = "0/15 * * * * ?")
	public void reportCurrentByCron() {
		if (custom.getSessionTimeOut() == 0) {
			return;
		}
		SessionData sd = null;
		Date lastDate = null;
		for (String key : Session.SESSION_MAP.keySet()) {
			sd = Session.SESSION_MAP.get(key);
			//获取最后操作时间
			lastDate = Session.LAST_OPERATION_MAP.get(key);
			if (isTimeOut(sd.getLoginDate(), lastDate, custom.getSessionTimeOut())) {
				logger.info(key+"loing time out, system cleaning login message.");
				Session.SESSION_MAP.remove(key);
				Session.LAST_OPERATION_MAP.remove(key);
			}
		}
	}
	
	/**
	 * 判断是否超时
	 * @param loginDate
	 * @param lastDate
	 * @param timeOut
	 * @return
	 */
	private boolean isTimeOut(Date loginDate, Date lastDate, int timeOut) {
		Calendar c = Calendar.getInstance();
		c.setTime(loginDate);
		c.add(Calendar.MINUTE, timeOut);
		//如果登录后未操作过，则取当前时间
		if (lastDate == null) {
			lastDate = new Date();
		}
		//当前登录
		if (c.getTime().before(lastDate)) {
			return true;
		}
		return false;
	}

}
