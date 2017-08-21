package com.xiangxun.atms.common.deptment.service;

import java.util.List;

import com.google.gson.JsonArray;
import com.xiangxun.atms.common.deptment.vo.Department;
import com.xiangxun.atms.common.deptment.vo.DepartmentSearch;
import com.xiangxun.atms.framework.base.BaseService;

/**
 * 组织机构服务接口
 * @author zhouhaij
 * @Apr 19, 2013 2:07:20 PM
 */
public interface DepartmentService extends BaseService<Department,DepartmentSearch>{
	
	public String DEPARTMENT_CACHE ="department_cache";
	public String DEPARTMENTCODE_CACHE ="departmentcode_cache";
	
	/***
	 * 获取所有的机构数据
	 * @return
	 */
	public List<Department> findAll();
	
	/***
	 * 获取所有的机构数据（除根节点之外）
	 * @return
	 */
	public List<Department> findAllBesidesRoot();
	
	/***
	 * 根据id获取所有的子节点
	 * @param id
	 * @return
	 */
	public List<Department> getChildren(String id);
	
	
	/***
	 * 根据id获取所有节点的子节点,包括传入的id所属对象
	 * @param id
	 * @return
	 */
	public List<Department> getAllChildDepartment(String id);
	
	/***
	 * 根据id获取所有节点的子节点
	 * @param id
	 * @return
	 */
	public List<String> getAllChildIds(String id);
	
	/***
	 * 判断是否有子节点
	 * @param id
	 * @return
	 */
	public boolean hasChild(String id);
	
	/***
	 * 刷新缓存
	 */
	public void refreshCache();
	
	public void refreshOrgCache();

	/***
	 * 根据编码获取部门
	 * @param code
	 * @return
	 */
	public Department getOneByCode(String code);
	
	/***
	 * 根据名称获取部门
	 * @param name
	 * @return
	 */
	public Department getOneByName(String name);
	
	/***
	 * 获取部门的json字符
	 * @param menuid
	 * @return
	 */
	public JsonArray getDeptJsonArray(String menuid);
	
	
	/***
	 * 根据menuid获取当前用户部门的父部门树
	 * @param id
	 * @return
	 */
	public List<Department> getParent(String menuid);
	
	/***
	 * 根据meunid获取当前用户部门
	 * @param menuid
	 * @return
	 */
	public List<Department> getDept(String menuid);
	
	/***
	 * 根据id获取父id
	 * @param id
	 * @return
	 */
	public String getParentIdById(String id);
	
	/***
	 * 按部门树的层数获取个数，用于自动生成部门编码
	 * @param level
	 * @return
	 */
	public int getCountByLevel(int level);
}
