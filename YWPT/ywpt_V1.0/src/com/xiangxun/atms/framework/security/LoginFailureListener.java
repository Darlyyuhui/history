package com.xiangxun.atms.framework.security;

import java.net.UnknownHostException;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.xiangxun.atms.common.log.service.SystemLogService;
import com.xiangxun.atms.common.log.vo.SystemLog;
import com.xiangxun.atms.framework.base.ApplicationContextHolder;
import com.xiangxun.atms.framework.log.Logging;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;

/**
 * 用户登录失败事件监听器
 * 
 */
public class LoginFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    /**
     * 日志
     */
	 protected static com.xiangxun.atms.framework.log.Logging logger = new Logging(LoginFailureListener.class);
	 
	 

    /**
     * 登录失败后事件处理
     */
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent authenticationFailureBadCredentialsEvent) {
      
    	logger.debugTitle("登陆监听器启动......");
    	
    	Authentication authentication = authenticationFailureBadCredentialsEvent.getAuthentication();
    	AbstractAuthenticationToken authenticationToken = (AbstractAuthenticationToken) authenticationFailureBadCredentialsEvent.getSource();
        WebAuthenticationDetails authenticationDetails = (WebAuthenticationDetails) authenticationToken.getDetails();
        
        String ipAddress = authenticationDetails.getRemoteAddress();
        if (ipAddress.equals("127.0.0.1")) {
            // 根据网卡取本机配置的IP
            java.net.InetAddress inet = null;
            try {
                inet = java.net.InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                logger.error("IP地址获取失败.", e);
            }
            if(inet != null)
            ipAddress = inet.getHostAddress();
        }
        
        //记录日志
        SystemLog log = new SystemLog();
        log.setId(UuidGenerateUtil.getUUIDLong());
        log.setContent("尝试登录系统失败.");
        log.setIpAddress(ipAddress);
        log.setOperatorId(authentication.getName());
        log.setOperatorName(authentication.getName());
        log.setOperationTime(DateUtil.now());
        log.setCreateDate(DateUtil.now());
        log.setCreateBy("匿名");
        log.setStatus("0");
        log.setType(2l);
        log.setLogLevel(2l);
        
        
        SystemLogService  systemLogService = (SystemLogService)ApplicationContextHolder.getBean("systemLogServiceImpl");
        systemLogService.save(log);
        
        logger.debugLine();
        logger.debug("登录用户："+ authentication.getName());
        logger.debug("尝试登录系统失败");
        logger.debugLine();
    }
}
