package com.xiangxun.atms.common.user.cache;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.xiangxun.atms.common.user.service.RoleService;
import com.xiangxun.atms.common.user.vo.Role;
import com.xiangxun.atms.framework.cache.BaseCache;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.log.Logging;

/***
 * 角色缓存
 * @author zhouhaij
 * @Apr 19, 2013 2:58:37 PM
 */
@Component
public class RoleCache implements BaseCache{
	private final Logging logger = new Logging(RoleCache.class);

	@Resource
	Cache cache;
	
	@Resource
	RoleService roleService;
	
	@Override
	public String getCacheKey() {
		return DIC_CACHE;
	}

	@Override
	public void init() throws Exception {
		List<Role> roles = roleService.findAll();
		Table<String, String, String> table = HashBasedTable.create();
		for (Role role : roles) {
			//存储的对象为table
			table.put(role.getId(), Role.class.getSimpleName(), role.getName());
		}
		cache.put(Role.class.getSimpleName(), table);
		
		logger.info("[角色缓存] 初始化完成");
	}

}
