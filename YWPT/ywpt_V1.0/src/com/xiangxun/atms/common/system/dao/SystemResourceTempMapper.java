package com.xiangxun.atms.common.system.dao;

import java.util.List;


import com.xiangxun.atms.common.system.vo.SystemResourceSearch;
import com.xiangxun.atms.common.system.vo.SystemResourceTemp;
import com.xiangxun.atms.framework.base.BaseMapper;

public interface SystemResourceTempMapper  extends BaseMapper<SystemResourceTemp,SystemResourceSearch>{
	/***
	 *  根据用户id获取桌面菜单
	 * @param userId
	 * @return
	 */
	public List<SystemResourceTemp> getDeskTopMenuByuserId(String userId);
	
	/***
	 * 获取用户拥有的菜单权限
	 * @param userId
	 * @return
	 */
	public List<SystemResourceTemp> getMenusByUserId(String userId,String menuType);
	
	/***
	 * 获取用户拥有的菜单权限
	 * @param userId
	 * @return
	 */
	public List<SystemResourceTemp> getAllMenusByUserId(String userId);
	
	/***
	 * 获取用户拥有的所有的叶菜单
	 * @param userId
	 * @return
	 */
	public List<SystemResourceTemp> getLeafMenusByUserId(String userId);
	
	/***
	 * 获取用户拥有的子菜单
	 * @param userId
	 * @return
	 */
	public List<SystemResourceTemp> getChildMenusByUserId(String userId,String parentId,String menuType);
	
	/***
	 * 根据用户以及菜单编号获取按钮的名称
	 * @param userId
	 * @param menuId
	 * @return
	 */
	public List<String> getMenuButtonsByParentId(String userId, String menuId);
	
	/***
	 * 根据角色id删除与资源的关系
	 * @param roleid
	 */
	public void deleteResourceByRoleId(String roleid);
	
	/***
	 * 添加角色与资源的关系
	 * @param roleid
	 * @param resid
	 */
	public void addRoleResource(String roleid,String resid);
	
	/***
	 * 根据角色id和资源id查找是否有记录
	 * @param roleid
	 * @return
	 */
	public int getRecordByResIdAndRoleId(String roleid,String resid);

	/**
	 * 熊杰添加<br/>
	 * 根据用户权限和指定的父菜单id查找所有子菜单，不包括操作权限
	 * @param userId
	 * @param parentId
	 * @return
	 */
	public List<SystemResourceTemp> getChildMenusByUserIdAndParentId(String userId,String parentId);
}
