package com.xiangxun.atms.framework.security;

import java.util.Collection;
import java.util.Iterator;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/***
 * 自定义访问策略类
 * @author zhouhaijian
 *
 */
public class AccessDecisionManagerBean implements AccessDecisionManager {

	  /**
     * 核心方法：如果不存在对该资源的定义，直接放行； 否则，如果找到正确的角色，即认为拥有权限，并放行， 否则throw new
     * AccessDeniedException("no right"); 这样，就会进入到的安全配置文件中 access-denied-page
     * 配置节中配置的页面，即403.jsp页面。
     */
    public void decide(Authentication authentication, Object object,
            Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        if(configAttributes == null){
            return ;
        }
        //org.slf4j.LoggerFactory
        Iterator<ConfigAttribute> ite=configAttributes.iterator();
        while(ite.hasNext()){
            ConfigAttribute ca=ite.next();
            String needRole=((SecurityConfig)ca).getAttribute();
            for(GrantedAuthority ga:authentication.getAuthorities()){
            	String author = "ROLE_" +ga.getAuthority();
                if(needRole.toUpperCase().equals(author.trim().toUpperCase())){  //ga is user's role.
                    return;
                }
            }
        }
        throw new AccessDeniedException("no right");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }


}
