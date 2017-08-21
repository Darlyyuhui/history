package com.xiangxun.atms.framework.security;

import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContext;

import com.xiangxun.atms.common.log.service.SystemLogService;
import com.xiangxun.atms.common.log.vo.SystemLog;
import com.xiangxun.atms.common.user.vo.User;
import com.xiangxun.atms.framework.base.ApplicationContextHolder;
import com.xiangxun.atms.framework.constant.FORMAT;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;

/**
 * Session监听器 完成对Seesion会话资源的实时监控
 * @author by google
 * @see HttpSessionBindingListener
 */
public class SessionListener implements HttpSessionListener {

    private static Log log = LogFactory.getLog(SessionListener.class);

    // 集合对象，保存session对象的引用
	static Hashtable<String,HttpSession> ht = new Hashtable<String,HttpSession>();

    /**
     * 实现HttpSessionListener接口，完成session创建事件控制 说明：此时的Session状态为无效会话，
     * 只有用户成功登录系统后才将此Session写入EAHTTPSESSION表作为有效SESSION来管理
     */
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        ht.put(session.getId(), session);
     
        log.debug("创建了一个Session连接:" + session.getId() + " " + DateUtil.formatDate(FORMAT.DATETIME.DEFAULT, new Date()));
    }

    /**
     * 实现HttpSessionListener接口，完成session销毁事件控制
     */
    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        Object context = session.getAttribute("SPRING_SECURITY_CONTEXT");
        if (context != null) {
            SecurityContext securityContext = (SecurityContext) context;
            OperatorDetails operatorDetails = (OperatorDetails) securityContext.getAuthentication().getPrincipal();
            
            if (operatorDetails != null) {
            	SystemLogService  systemLogService = (SystemLogService)ApplicationContextHolder.getBean("systemLogServiceImpl");
            	 SystemLog log = new SystemLog();
                 // 记录在线用户信息
                 User user = new User();
                 user.setName(operatorDetails.getUsername());
                 UserThreadLocal.set(user);

                 log.setId(UuidGenerateUtil.getUUIDLong());
                 //记录日志
                 log.setContent("成功退出系统.");
                 log.setIpAddress(SpringSecurityUtils.getCurrentUserIp());
                 log.setOperatorId(operatorDetails.getAccount());
                 log.setOperatorName(operatorDetails.getRealName());
                 log.setOperationTime(DateUtil.now());
                 log.setType(2l);
                 log.setLogLevel(1l);
                 log.setCreateDate(DateUtil.now());
                 log.setCreateBy(operatorDetails.getAccount());
                 log.setStatus("0");
            	  // 存储日志
                 systemLogService.save(log);
            }
            
        }
        ht.remove(session.getId());
    }    


    /**
     * 增加一个有效Session
     * 
     * @param session
     */
    static public void addSession(HttpSession session, User userVo) {
        ht.put(session.getId(), session);
    }

    /**
     * 返回全部session对象集合
     * 
     * @return
     */
    @SuppressWarnings("rawtypes")
	static public Iterator getSessions() {
        return ht.values().iterator();
    }

    /**
     * 依据session id返回指定的session对象
     * 
     * @param sessionId
     * @return
     */
    static public HttpSession getSessionByID(String sessionId) {
        return (HttpSession) ht.get(sessionId);
    }
}
