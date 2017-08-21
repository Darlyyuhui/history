package com.xiangxun.atms.common.user.service.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.common.deptment.service.DepartmentService;
import com.xiangxun.atms.common.deptment.vo.Department;
import com.xiangxun.atms.common.user.service.DepartmentAuthorityFilter;
import com.xiangxun.atms.framework.security.OperatorDetails;
import com.xiangxun.atms.framework.security.SpringSecurityUtils;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.module.property.service.DeviceInfoService;

/**
 * 用户部门数据权限过滤器
 * 
 * @author zhouhaij
 * @since 1.0
 * @version
 */
@Service
public class DepartmentAuthorityFilterImpl extends AbstractAuthorityContext implements DepartmentAuthorityFilter {

	@Resource
	DeviceInfoService deviceInfoService;

	@Resource
	DepartmentService departmentService;

	@Resource
	VideoValidator videoValidator;
	
	@Resource
	CrossValidator crossValidator;
	
	@Resource
	CountCrossValidator countCrossValidator;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xiangxun.atms.common.user.service.DepartmentAuthorityFilter#
	 * filter(java.util.Collection, java.lang.String)
	 */
	@Override
	public List<Department> filter(List<Department> collection, String menuid) {
		OperatorDetails details = SpringSecurityUtils.getCurrentUser();
		// 如果未开启部门权限扩展控制，则直接返回
		if (!authorityEnable()) {
			return collection;
		}
		if (!authorityMapIsEmpty()) {
			//如果此菜单所在的模块未开启数据权限，则按基础的默认权限处理
			if(!isEnable(menuid) || !contains(menuid)){
				return filterByDeptId(collection, details.getDeptId());
			}
			for (Iterator<Department> iterator = collection.iterator(); iterator.hasNext();) {
				Department department = (Department) iterator.next();
				// 获取部门Id
				String departmentId = department.getId();
				removeByDepartmentId(menuid, details, getUsersAuthorityMap(menuid), iterator, departmentId);
			}
			return collection;
		}
		
		return filterByDeptId(collection, details.getDeptId());
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.user.service.AuthorityFilterContext#isEnable(java.lang.String)
	 */
	@Override
	public boolean isEnable(String menuid) {
		boolean status = false;
		//为true说明是视频监控模块
		if(videoValidator.isDeviceCode(menuid,status)){
			//如果未启用视频监控模块，则按默认权限处理
			if(videoValidator.enable()){
				return true;
			}
		}
		if(crossValidator.isDeviceCode(menuid,status)){
			//如果未启用视频监控模块，则按默认权限处理
			if(crossValidator.enable()){
				return true;
			}
		}
		if(countCrossValidator.isDeviceCode(menuid,status)){
			//如果未启用视频监控模块，则按默认权限处理
			if(countCrossValidator.enable()){
				return true;
			}
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.user.service.impl.AbstractAuthorityContext#getBasePerssionSQL()
	 */
	@Override
	protected String getBasePerssionSQL() {
		return MessageFormat.format(UserAuthoritySQL.BASE_AUTHORITY_SQL, getDeptSQL());
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.user.service.impl.AbstractAuthorityContext#getBasePerssionExtSQL()
	 */
	@Override
	protected String getBasePerssionExtSQL(String menuid) {
		return MessageFormat.format(UserAuthoritySQL.BASE_AUTHORITY_SQL, getPermissonSQL(menuid));
	}
	
	/***
	 * 
	 * @param collection
	 * @param deptid
	 * @return
	 */
	private List<Department> filterByDeptId(List<Department> collection, String deptid) {
		for (Iterator<Department> iterator = collection.iterator(); iterator.hasNext();) {
			Department department = (Department) iterator.next();
			removeByDepartmentId(deptid, iterator, department.getId());
		}
		return collection;
	}

	/***
	 * 获取树的父节点树
	 * @param collection
	 * @param deptid
	 * @return
	 */
	private List<Department> filterByDeptidTree(List<Department> collection, String deptid) {
		List<Department> list = new ArrayList<Department>();
		for (Department department : collection) {
			list.add(department);
		}
		for (Iterator<Department> iterator = collection.iterator(); iterator.hasNext();) {
			Department department = (Department) iterator.next();
			removeByDepartmentParentId(deptid, iterator, department.getId());
		}
		return collection;
	}
	
	/**
	 * @param menuid
	 * @param details
	 * @param deptMenuMap
	 * @param iterator
	 * @param departmentId
	 */
	private void removeByDepartmentId(String menuid, OperatorDetails details, Map<String, List<String>> deptMenuMap, Iterator<Department>  iterator, String departmentId) {
		// 根据部门Id获取对应的menuid
		List<String> menus = deptMenuMap.get(departmentId);
		// 是否留下的标志
		boolean reverseflag = false;
		if (menus != null) {
			for (String string : menus) {
				if (!StringUtils.isEmpty(string)) {
					if (string.equals(menuid)) {
						reverseflag = true;
						break;
					}
				}
			}
		}
		// 如果未符合权限的要求则删除
		if (!reverseflag) {
			iterator.remove();
		}
	}

	/**
	 * 根据当前部门及子部门进行过滤
	 * 
	 * @param deptid
	 * @param departs
	 * @param iterator
	 * @param departmentId
	 */
	private void removeByDepartmentId(String deptid, Iterator<Department>  iterator, String departmentId) {
		// 找出当前部门的子部门
		List<Department> departs = getAllDepartmentByUserId(deptid);
		boolean reverseflag = false;
		// 如果刚好部门id一致，则继续查找其他部门
		if (deptid.equals(departmentId)) {
			return;
		} else {
			// 继续查找子部门
			for (Department department : departs) {
				//修复出现空指针的漏洞
				if(department==null){
					iterator.remove();
				}
				if (department.getId().equals(departmentId)) {
					reverseflag = true;
					break;
				}
			}
		}
		// 如果没找到，则删除该记录
		if (!reverseflag) {
			iterator.remove();
		}
	}

	/**
	 * 根据当前部门获取当前部门的父节点部门过滤
	 * 
	 * @param deptid
	 * @param departs
	 * @param iterator
	 * @param departmentId
	 */
	private void removeByDepartmentParentId(String deptid, Iterator<Department>  iterator, String departmentId) {
		// 找出当前部门的子部门
		List<Department> departs = getAllDepartmentParnetByUserId(deptid);
		boolean reverseflag = false;
		// 如果刚好部门id一致，则继续查找其他部门
		if (deptid.equals(departmentId)) {
			return;
		} else {
			// 继续查找子部门
			for (Department department : departs) {
				//修复出现空指针的漏洞
				if(department==null){
					iterator.remove();
				}
				if (department.getId().equals(departmentId)) {
					reverseflag = true;
					break;
				}
			}
		}
		// 如果没找到，则删除该记录
		if (!reverseflag) {
			iterator.remove();
		}
	}

	
	@Override
	public List<Department> filterTree(List<Department> collection,
			String menuid) {

		OperatorDetails details = SpringSecurityUtils.getCurrentUser();
		// 如果未开启部门权限扩展控制，则直接返回
		if (!authorityEnable()) {
			return collection;
		}
		if (!authorityMapIsEmpty()) {
			//如果此菜单所在的模块未开启数据权限，则按基础的默认权限处理
			if(!isEnable(menuid)){
				
				//如果是管理不需要做任何过滤
				if(details.getDeptId().equals("00"))
				{
					return filterByDeptId(collection, details.getDeptId());
				}
				
				return filterByDeptidTree(collection, details.getDeptId());
			}
			for (Iterator<Department>  iterator = collection.iterator(); iterator.hasNext();) {
				Department department = (Department) iterator.next();
				// 获取部门Id
				String departmentId = department.getId();
				removeByDepartmentId(menuid, details, getUsersAuthorityMap(menuid), iterator, departmentId);
			}
			return collection;
		}
		
		return filterByDeptId(collection, details.getDeptId());
	
	}



}
