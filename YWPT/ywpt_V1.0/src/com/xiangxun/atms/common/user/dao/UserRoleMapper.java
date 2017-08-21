package com.xiangxun.atms.common.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiangxun.atms.common.user.vo.Role;
import com.xiangxun.atms.common.user.vo.User;
import com.xiangxun.atms.common.user.vo.UserRole;

/***
 * 用户与角色管理映射器
 * @author zhouhaij
 */
public interface UserRoleMapper {

	/***
	 * 添加关联映射
	 * @return
	 */
	void save(UserRole userRole);
	
	/***
	 * 根据用户id批量删除用户角色关系
	 * @param users
	 * @return
	 */
	int batchDeleteByUsers(@Param("records") String... values);
	
	/***
	 * 根据角色id批量删除用户角色关系
	 * @param roleids
	 * @return
	 */
	int batchDeleteByRoles(@Param("records") String... values);
	
	
	/**
	 * 根据角色id，获取该角色下的所有用户
	 * @param roleid
	 * @return
	 */
	List<User> getUserByRoleId(String roleid);
	
	/***
	 * 根据用户id获取该用户拥有的所有角色
	 * @param userid
	 * @return
	 */
	List<Role> getRolesByUserId(String userid);
}
