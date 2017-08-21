package com.xiangxun.atms.common.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.common.system.service.ResourceService;
import com.xiangxun.atms.common.system.vo.SystemResource;
import com.xiangxun.atms.common.user.service.MenuCacheService;
import com.xiangxun.atms.common.user.service.UserRoleService;
import com.xiangxun.atms.common.user.type.MenuType;
import com.xiangxun.atms.common.user.vo.User;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.log.Logging;
/**
 * <p>菜单cache服务类</p>
 * @author zhouhaij
 * @since 1.0
 * @version
 */
@Service
public class MenuCacheServiceImpl implements MenuCacheService{
	
	private Logging logger = new Logging(MenuCacheServiceImpl.class);
	
	@Resource
	Cache cache;
	
	@Resource
	ResourceService menuService;
	
	@Resource
	UserRoleService userRoleService;
	
	@Override
	public void cacheMenu(String userid) {
		//获取用户拥有的可操作的菜单
		List<SystemResource> menus = menuService.getMenusByUserId(userid,MenuType.MODULE);
		for (SystemResource menu : menus) {
			List<String> btns = menuService.getMenuButtonsByParentId(userid,menu.getId());
			logger.debug("btns:"+btns);
			//对菜单进行缓存处理
			cacheUserMenu(userid,menu.getId(),btns);
		}
	}

	/***
	 * 对可操作的菜单进行cache
	 * @param userId 用户id
	 * @param parentId 操作按钮的父节点id
	 * @param menuTexts 操作按钮的text
	 * @param value
	 */
	public void cacheUserMenu(String userId,String parentId,List<String> menuTexts){
		logger.debug("正在缓存按钮菜单 ");
		try{
			logger.debug("menuTexts");
			cache.put(getMenuButtonCacheKey(userId, parentId), menuTexts);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * 获取缓存的菜单
	 * @param userId
	 * @param parentId
	 * @param menuText
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getMenuCache(String userId,String parentId){
		return (List<String>) cache.get(getMenuButtonCacheKey(userId, parentId));
	}
	


	/***
	 * 获取缓存的菜单cache的key值
	 * @param userId
	 * @param parentId
	 * @param menuText 操作按钮的text
	 * @return
	 */
	private String getMenuButtonCacheKey(String userId,String parentId){
		String key = userId+"_"+parentId;
		return key;
	}

	@Override
	public void refreshCache(String roleid) {
		List<User> users =userRoleService.getUserByRoleId(roleid);
		for (User user : users) {
			cacheMenu(user.getId());
		}
	}
}
