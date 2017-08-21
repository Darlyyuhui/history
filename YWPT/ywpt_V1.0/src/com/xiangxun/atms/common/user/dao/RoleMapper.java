package com.xiangxun.atms.common.user.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.xiangxun.atms.common.user.vo.Role;
import com.xiangxun.atms.common.user.vo.RoleSearch;
import com.xiangxun.atms.framework.base.BaseMapper;

/***
 * 角色数据访问接口
 * @author zhouhaij
 */
public interface RoleMapper extends BaseMapper<Role,RoleSearch>{
	
	/***
	 * 根据角色属性获取角色对应的系统资源
	 * @param role
	 * @return
	 */
	List<Role> getRoleResourceContent(Role role);
	
	
	/***
	 * 根据角色属性获取角色对应的系统资源,支持分页
	 * @param role
	 * @return
	 */
    List<Role> selectRoles(Role role,RowBounds rowBounds);
    
	/***
	 * 根据角色名称查找对应的角色
	 * @param name
	 * @return
	 */
	public int getRolesByName(String name);
}