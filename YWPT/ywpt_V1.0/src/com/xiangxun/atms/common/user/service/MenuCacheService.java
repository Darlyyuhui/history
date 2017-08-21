package com.xiangxun.atms.common.user.service;

import java.util.List;
/**
 * <p>菜单cache服务类</p>
 * @author zhouhaij
 * @since 1.0
 * @version
 */
public interface MenuCacheService {
	
	/***
	 * 根据用户缓存对应的可操作菜单
	 * @param user
	 */
	public void cacheMenu(String userId);
	
	
	/***
	 * 对可操作的菜单进行cache
	 * @param userId 用户id
	 * @param parentId 操作按钮的父节点id
	 * @param menuText 操作按钮的text
	 * @param value
	 */
	public void cacheUserMenu(String userId,String parentId,List<String> menuTexts);
	
	/***
	 * 获取缓存的菜单
	 * @param userId
	 * @param parentId
	 * @param menuText
	 * @return
	 */
	public List<String> getMenuCache(String userId,String parentId);
	
	
	/***
	 * 根据角色，刷新操作缓存 
	 * @param roleid
	 */
	public void refreshCache(String roleid);
		
	
}
