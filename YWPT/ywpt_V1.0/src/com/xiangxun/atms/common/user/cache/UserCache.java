package com.xiangxun.atms.common.user.cache;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.xiangxun.atms.common.user.service.UserService;
import com.xiangxun.atms.common.user.vo.User;
import com.xiangxun.atms.framework.cache.BaseCache;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.log.Logging;

/**
 * 用户缓存
 * @author zhouhaij
 * @Apr 19, 2013 1:58:36 PM
 */
@Component
public class UserCache implements BaseCache {
	private final Logging logger = new Logging(UserCache.class);
	public String USERNAME_CACHE ="username_cache";
	public String USERNAMEBYZH = "usernamebyzh";
	@Resource 
	UserService userService;
	
	@Resource
	Cache cache;

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.framework.cache.BaseCache#getCacheKey()
	 */
	@Override
	public String getCacheKey() {
		return DIC_CACHE;
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.framework.cache.BaseCache#init()
	 */
	@Override
	public void init() throws Exception {
		List<User> users = userService.findAll(false);
		cache.put(User.class.getSimpleName(), users);
		//add by kouyunhao 2014-01-09 添加用户缓存，为道路挖占所用。
		Table<String, String, String> table = HashBasedTable.create();
		for (User user : users) {
			//存储的对象为table
			table.put(user.getId(), USERNAME_CACHE, user.getName());
		}
		cache.put(USERNAME_CACHE, table);
		//add by yantao 2014-04-29 添加用户缓存，为短信发送日志所用。
		Table<String, String, String> table2 = HashBasedTable.create();
		for (User user : users) {
			//存储的对象为table
			table2.put(user.getAccount(), USERNAMEBYZH, user.getName());
		}
		cache.put(USERNAMEBYZH, table2);
		logger.info("[用户信息] 缓存初始化完成");
	}

}
