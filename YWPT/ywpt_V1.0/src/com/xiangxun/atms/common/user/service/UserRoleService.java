package com.xiangxun.atms.common.user.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiangxun.atms.common.user.vo.User;
import com.xiangxun.atms.common.user.vo.UserRole;

/***
 * 用户角色关系接口
 * @author zhouhaij
 */
public interface UserRoleService {

	/***
	 * 添加关联映射
	 * @return
	 */
	void save(UserRole... userRole);
	
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
	
}
