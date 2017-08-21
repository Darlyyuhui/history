package com.xiangxun.atms.common.user.cache;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.xiangxun.atms.common.deptment.service.DepartmentService;
import com.xiangxun.atms.common.deptment.vo.Department;
import com.xiangxun.atms.framework.cache.BaseCache;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.log.Logging;
/***
 * 组织机构缓存
 * @author zhouhaij
 * @Apr 19, 2013 2:04:23 PM
 */
@Component
public class DepartmentCache implements BaseCache{
	private final Logging logger = new Logging(DepartmentCache.class);
	@Resource
	Cache cache;

	@Resource
	DepartmentService departmentService;
	
	@Override
	public String getCacheKey() {
		return DIC_CACHE;
	}

	@Override
	public void init() throws Exception {
		logger.info("[部门信息] 缓存初始化开始");
		List<Department> caches =  departmentService.findAll();
		Table<String, String, String> table = HashBasedTable.create();
		//存入对象数据
		Table<String, String, Department> departTable = HashBasedTable.create();
		for (Department department : caches) {
			//存储的对象为table
			table.put(department.getId(), Department.class.getSimpleName(), department.getName());
			//存入对象
			departTable.put(department.getId(), department.getParentid(), department);
		}
		cache.put(Department.class.getSimpleName(), table);
		//存入对象数据
		cache.put(DepartmentService.DEPARTMENT_CACHE, departTable);
		logger.info("[部门信息] 缓存初始化完成");
	}

}
