package com.xiangxun.atms.common.user.service;

import java.util.List;

import com.xiangxun.atms.common.deptment.vo.Department;

/**
 * <p>用户部门数据权限过滤器</p>
 * @author zhouhaij
 * @version
 */
public interface DepartmentAuthorityFilter extends AuthorityFilterContext{
	

	
	/***
	 * 用于对业务数据进行部门数据权限的自动过滤
	 * @param collection 要过滤的数据集合
	 * @param menuid     菜单编号
	 * @return
	 */
	public List<Department>  filter(List<Department> collection,String menuid);
	
	/***
	 * 用于对业务数据进行部门数据权限的自动过滤,得到的是一个部门树而不是获取这个部门
	 * @param collection 要过滤的数据集合
	 * @param menuid     菜单编号
	 * @return
	 */
	public List<Department>  filterTree(List<Department> collection,String menuid);
	
	
}
