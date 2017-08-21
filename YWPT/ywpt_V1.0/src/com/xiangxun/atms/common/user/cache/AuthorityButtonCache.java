package com.xiangxun.atms.common.user.cache;

import java.util.List;

import com.xiangxun.atms.common.user.service.MenuCacheService;
import com.xiangxun.atms.common.user.service.UserService;
import com.xiangxun.atms.common.user.vo.User;
import com.xiangxun.atms.framework.cache.BaseCache;
import com.xiangxun.atms.framework.log.Logging;
/**
 * <p>用户权限按钮cache</p>
 * @author zhouhaij
 * @since 1.0
 * @version
 */
public class AuthorityButtonCache implements BaseCache{
	
	@javax.annotation.Resource
	UserService userService;
	
	@javax.annotation.Resource
	MenuCacheService menuCacheService;
	
	private final Logging logger = new Logging(AuthorityButtonCache.class);

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.framework.cache.BaseCache#init()
	 */
	@Override
	public void init() throws Exception {
		List<User> users = userService.findAll(false);
		for (User user : users) {
			//缓存用户拥有的可操作菜单
			menuCacheService.cacheMenu(user.getId());
		}
		
		logger.info("[用户权限按钮] 缓存初始化完成");
	}

	@Override
	public String getCacheKey() {
		return "dicCache";
	}

}
