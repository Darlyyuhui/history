package com.xiangxun.atms.common.user.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xiangxun.atms.common.system.service.ResourceService;
import com.xiangxun.atms.common.system.vo.SystemResource;
import com.xiangxun.atms.framework.cache.BaseCache;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.log.Logging;
import com.xiangxun.atms.framework.resource.MessageResources;
import com.xiangxun.atms.framework.util.StringUtils;

/**
 * <p>需要进行数据权限控制的菜单缓存</p>
 * @author zhouhaij
 * @since 1.0
 * @version
 */
public class AuthorityMenuCache implements BaseCache{
	private final Logging logger = new Logging(AuthorityMenuCache.class);
	public static String AUTHORITY_MENUS = "com.xiangxun.atms.authority.menus";
	
	@Resource
	Cache cache;
	
	@Resource
	ResourceService resourceService;
	/* (non-Javadoc)
	 * @see com.xiangxun.atms.framework.cache.BaseCache#init()
	 */
	@Override
	public void init() throws Exception {
		//获取资源管理器
		MessageResources resource = MessageResources.getMessageInstance(null,null);
		String[] menuCodes = resource.getMessage("sys.module.code").split(",");
		String[] menus = resource.getMessage("sys.module").split(",");
		Map<String,List<SystemResource>> menuMap = new HashMap<String,List<SystemResource>>();
		for (int i = 0;i < menus.length;i++) {
			String sysexinclude = resource.getMessage("sys.module.exinclude");
			String exinclude = resource.getMessage("sys.module."+menuCodes[i]+".exinclude");
			if(StringUtils.notEmpty(exinclude)){
				sysexinclude = sysexinclude+","+exinclude;
			}
			List<SystemResource> systemResources = resourceService.findAllMenuByKeyWord(menus[i],sysexinclude);
			String type = menuCodes[i];
			menuMap.put(type, systemResources);
		}
		cache.put(AUTHORITY_MENUS, menuMap);
		
		logger.info("[数据权限控制菜单] 缓存初始化完成");
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.framework.cache.BaseCache#getCacheKey()
	 */
	@Override
	public String getCacheKey() {
		return DIC_CACHE;
	}

}
