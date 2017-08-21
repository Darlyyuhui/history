package com.xiangxun.atms.common.user.dao;

import java.util.List;

import com.xiangxun.atms.common.system.vo.SystemResource;

//import com.xiangxun.atms.common.user.vo.SysMobileUserRole;
//import com.xiangxun.atms.common.user.vo.SysResource;

public interface SysMobileUserRoleMapper {

	//public void save(SysMobileUserRole item);
	
	//public void deleteByUserId(String userId);
	
	/**
	 * 删除权限时验证是否被使用
	 * @param roleId
	 * @return
	 *//*
	public int validateUse(String roleId);*/
	
	public List<SystemResource> getResourceByUserId(String userId);
	
}
