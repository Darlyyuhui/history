package com.xiangxun.atms.common.system.service;

import java.util.List;

import com.xiangxun.atms.common.system.vo.SystemResource;
import com.xiangxun.atms.common.system.vo.SystemResourceSearch;
import com.xiangxun.atms.common.system.vo.SystemResourceTemp;
import com.xiangxun.atms.common.user.type.MenuType;
import com.xiangxun.atms.framework.base.BaseService;

public interface ResourceTempService extends BaseService<SystemResourceTemp,SystemResourceSearch>{
	
	public List<SystemResourceTemp> findAll(boolean disabled);
	
	/***
	 * 获取所有的菜单
	 * @param keyword
	 * @return
	 */
	public List<SystemResourceTemp> findAllMenuByKeyWord(String keyWord,String exIncludes);
	
	/**
	 * 获取资源的孩子节点
	 * @param menuid
	 * @return
	 */
	public List<SystemResourceTemp> getChildren(String menuid,MenuType type);
	
	/***
	 * 判断该资源是否有孩子
	 * @param menuid
	 * @return
	 */
	public boolean hasChild(String menuid,MenuType type);
	/***
	 * 获取用户拥有的菜单权限
	 * @param userId
	 * @return
	 */
	public List<SystemResourceTemp> getMenusByUserId(String userId,MenuType menuType);
	/***
	 * 获取用户拥有的菜单权限
	 * @param userId
	 * @return
	 */
	public List<SystemResourceTemp> getAllMenusByUserId(String userId);
	/***
	 * 获取用户拥有的菜单权限
	 * @param userId
	 * @return
	 */
	public List<SystemResourceTemp> getChildMenusByUserId(String userId,String parentId,MenuType menuType);
	/***
	 * 获取用户拥有的所有的叶菜单
	 * @param userId
	 * @return
	 */
	public List<SystemResourceTemp> getLeafMenusByUserId(String userId);
	
	/***
	 *  获取用户拥有的快捷方式
	 * @param userId
	 * @return
	 */
	public List<SystemResourceTemp> getDeskTopMenuByuserId(String userId);
	/****
	 * 获取menu下的所有按钮
	 * @param userId
	 * @param menuId
	 * @return
	 */
	public List<String> getMenuButtonsByParentId(String userId,String menuId);
	
	/***
	 * 
	 * @param userId
	 * @return
	 */
	public List<SystemResourceTemp> getMenuButtonsByUserId(String userId);
	
	/***
	 * 授权角色资源
	 * @param roleid
	 * @param resid
	 */
	public void grantRoleResource(String roleid,String... resid);
	
	
	/***
	 * 根据角色id和资源id查找是否有记录
	 * @param roleid
	 * @return
	 */
	public boolean hasResourceByRoleId(String roleid,String resid);
	
	/**
	 * 熊杰添加<br/>
	 * 根据用户权限和菜单名称获取所有子菜单，不包括操作资源
	 * @param userId 对应用户的id
	 * @param parentId 父模块id
	 * @return
	 */
	public List<SystemResourceTemp> getChildMenusByUserIdAndParentId(String userId,String parentId);
	
}

