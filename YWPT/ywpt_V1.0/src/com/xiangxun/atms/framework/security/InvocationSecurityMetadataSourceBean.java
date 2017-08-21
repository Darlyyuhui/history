package com.xiangxun.atms.framework.security;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.xiangxun.atms.common.system.service.ResourceService;
import com.xiangxun.atms.common.system.vo.SystemResource;
import com.xiangxun.atms.common.user.service.RoleService;
import com.xiangxun.atms.common.user.vo.Role;
import com.xiangxun.atms.framework.base.ApplicationContextHolder;
import com.xiangxun.atms.framework.log.Logging;

/**
 * 
 * 自定义角色资源过滤类
 * @author zhouhaijian
 * @version 1.0
 * 
 */
public class InvocationSecurityMetadataSourceBean implements FilterInvocationSecurityMetadataSource {
    
    private RequestMatcher urlMatcher = null;
    
    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
    
    private Logging logger = new Logging(InvocationSecurityMetadataSourceBean.class);

    public InvocationSecurityMetadataSourceBean() {
        loadResourceDefine();
    }
    
    /**
     * 加载资源定义，决定了哪些资源应该有什么样的角色访问
     */
    private void loadResourceDefine() {
    	
    	 resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
         RoleService roleService = (RoleService)ApplicationContextHolder.getBean("roleService");
         ResourceService resourceService = (ResourceService)ApplicationContextHolder.getBean("resourceService");
         List<Role> roles = (List<Role>) roleService.findAll();
         for (Role role : roles) {
             ConfigAttribute configAttribute = new SecurityConfig("ROLE_" + role.getCode());
             Set<SystemResource> resources = role.getResources();
             for (SystemResource resource : resources) {
            	 if(resource.getContent()==null){
            		 continue;
            	 }
                 logger.debug("获得角色【" +role.getName() + "】拥有的action有：" + resource.getContent());
                 String[] urls = null;
                 if (null != resource.getContent() && resource.getContent().indexOf(",") != -1) {
                     urls = resource.getContent().split(",");
                 } else {
                     urls = new String[] { StringUtils.removeEnd(resource.getContent(),"*") };
                 }
                 for (int i = 0; i < urls.length; i++) {
                     if (resourceMap.containsKey("/" + urls[i])) {
                         Collection<ConfigAttribute> configAttributes = resourceMap.get("/" + urls[i]);
                         configAttributes.add(configAttribute);
                         resourceMap.put("/" + urls[i], configAttributes);
                     } else {
                         Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
                         configAttributes.add(configAttribute);
                         resourceMap.put("/" + urls[i], configAttributes);
                     }
                 }
             }
         }
         /**
          * 在资源表中的资源并且没有在权限系统中定义的，默认定义为ROLE_SYSTEM角色访问权限。
          * 原因是没有在权限表中定义的资源默认是放行的，但在系统中资源表中的资源如果没有分配给某个角色意味着没有访问权限。
          */
         List<SystemResource> resvoList = (List<SystemResource>) resourceService.findAll(false);
         for (SystemResource resource : resvoList) {
             if (null != resource.getContent()) {
                 String url = resource.getContent();
                 if (!resourceMap.containsKey(url)) {
                     ConfigAttribute configAttribute = new SecurityConfig("ROLE_SYSTEM");
                     Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
                     configAttributes.add(configAttribute);
                     resourceMap.put(url, configAttributes);
                 }
             }
         }
    	
    }

    // According to a URL, Find out permission configuration of this URL.
    public Collection<ConfigAttribute> getAttributes(Object object)
            throws IllegalArgumentException {
    	 FilterInvocation filterInvocation = ((FilterInvocation)object);
         Iterator<String> ite = resourceMap.keySet().iterator();
         while (ite.hasNext()) {
             String resURL = ite.next();
             if(!StringUtils.isEmpty(resURL)){
                 urlMatcher  = new AntPathRequestMatcher(resURL);
                 if (urlMatcher.matches(filterInvocation.getHttpRequest())) {
                     return resourceMap.get(resURL);
                 }
             }
         }
         return null;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }
    
    public Collection<ConfigAttribute> getAllConfigAttributes() {
    	  Iterator<String> ite = resourceMap.keySet().iterator();
    	  while (ite.hasNext()) {
              String resURL = ite.next();
               return resourceMap.get(resURL);
          }
    	  return null;
    }

    //添加权限刷新方法
    public void refreshRoleResource() {
        loadResourceDefine();
    }
    
}

