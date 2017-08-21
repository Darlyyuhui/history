package com.xiangxun.atms.common.user.service;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.common.user.vo.Role;
import com.xiangxun.atms.common.user.vo.RoleSearch;
import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.framework.base.Page;
/***
 * 角色服务接口
 * @author zhouhaij
 */
public interface RoleService extends BaseService<Role,RoleSearch>{
	
	/***
	 * 获取所有的角色
	 * @return
	 */
	List<Role> findAll();

	/***
	 * 根据条件获取角色
	 * @param role
	 * @return
	 */
	List<Role> getRoleResourceContent(Role role);
	
	
	boolean containsName(String name);
	
	/***
	 * 获取用户的分页列表数据
	 * @param orgid
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page getRolesByCondition(Map<String, Object> map,int pageNo,int pageSize,String sortType);
}
