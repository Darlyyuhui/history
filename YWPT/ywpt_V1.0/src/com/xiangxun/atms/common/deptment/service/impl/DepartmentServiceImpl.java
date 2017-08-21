package com.xiangxun.atms.common.deptment.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.xiangxun.atms.common.deptment.dao.DepartmentMapper;
import com.xiangxun.atms.common.deptment.service.DepartmentService;
import com.xiangxun.atms.common.deptment.vo.Department;
import com.xiangxun.atms.common.deptment.vo.DepartmentSearch;
import com.xiangxun.atms.common.user.service.DepartmentAuthorityFilter;
import com.xiangxun.atms.common.user.service.UserService;
import com.xiangxun.atms.common.user.vo.UserSearch;
import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.cache.Cache;
/***
 * 组织机构服务类实现
 * @author zhouhaij
 * @Apr 19, 2013 2:08:56 PM
 */
@Service
public class DepartmentServiceImpl extends AbstractBaseService<Department, DepartmentSearch> implements DepartmentService{

	@Resource
    DepartmentMapper departmentMapper;
	
	@Resource
	DepartmentAuthorityFilter departmentAuthorityFilter;

	@Resource
	Cache cache;
	
	@Resource
	UserService userService;
	
	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.user.service.DepartmentService#findAll()
	 */
	@Override
	public List<Department> findAll() {
		DepartmentSearch search = new DepartmentSearch();
		search.createCriteria().andIdIsNotNull();
		search.setOrderByClause("code");
		return departmentMapper.selectByExample(search);
	}

	/* (non-Javadoc)
	 * @see@see com.xiangxun.atms.common.deptment.service.DepartmentService#findAllBesidesRoot()
	 */
	@Override
	public List<Department> findAllBesidesRoot() {
		DepartmentSearch search = new DepartmentSearch();
		search.createCriteria().andIdIsNotNull().andIdNotEqualTo("00");
		search.setOrderByClause("code");
		return departmentMapper.selectByExample(search);
	}
	
	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.deptment.service.DepartmentService#getChildren(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Department> getChildren(String id) {
		Assert.notNull(id,"id must not be null");
		Table<String, String, Department> departTable = (Table<String, String, Department>)cache.get(DEPARTMENT_CACHE);
		//如果缓存中不存在，则查询数据库
		if(departTable ==null){
			return departmentMapper.getLeafNodeById(id);
		}else{
			Map<String,Department> childDepart = departTable.column(id);
			List<Department> resultList = new ArrayList<Department>();
			for (Iterator<String> iterator = childDepart.keySet().iterator(); iterator.hasNext();) {
				String departmentId = (String) iterator.next();
				resultList.add(childDepart.get(departmentId));
			}
			Collections.sort(resultList,new Comparator<Department>() {
				@Override
				public int compare(Department o1, Department o2) {
					return o1.getCode().hashCode()-o2.getCode().hashCode();
				}
				
			});
			return resultList;
		}
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.deptment.service.DepartmentService#hasChild(java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public boolean hasChild(String id) {
		Assert.notNull(id,"id must not be null");
		Table<String, String, Department> departTable = (Table<String, String, Department>)cache.get(DEPARTMENT_CACHE);
		//如果缓存中不存在，则查询数据库
		if(departTable ==null){
			return departmentMapper.hasChild(id)>0;
		}else{
			Map<String,Department> childDepart = departTable.column(id);
			return !childDepart.isEmpty();
		}
	}
	

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.framework.base.AbstractBaseService#getById(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Department getById(String id) {
		Assert.notNull(id,"id must not be null");
		Table<String, String, Department> departTable = (Table<String, String, Department>)cache.get(DEPARTMENT_CACHE);
		//如果缓存中不存在，则查询数据库
		if(departTable !=null){
			Map<String,Department> depart = departTable.row(id);
			for (Iterator<String> it = depart.keySet().iterator(); it.hasNext();) {
				String departid = (String) it.next();
				return depart.get(departid);
			}
		}
		return super.getById(id);
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.framework.base.AbstractBaseService#deleteById(java.lang.String)
	 */
	@Override
	@Transactional
	public int deleteById(String id) {
		Assert.notNull(id,"id must not be null");
		
		List<Department> children = getAllChildDepartment(id);
		List<String> ids = new ArrayList<String>();
		for (Department res : children) {
			ids.add(res.getId());
		}
		ids.add(id);
		
		DepartmentSearch search = new DepartmentSearch();
		search.createCriteria().andIdIn(ids);
		
		UserSearch userSearch = new UserSearch();
		userSearch.createCriteria().andDeptidIn(ids);
		
		//删除部门下的用户
		userService.deleteByExample(userSearch);
		
		//删除的子节点
		int childen = deleteByExample(search);
		return childen;
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.deptment.service.DepartmentService#refreshCache()
	 */
	@Override
	public void refreshCache() {
		List<Department> caches =  findAll();
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
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.framework.base.AbstractBaseService#save(java.lang.Object)
	 */
	@Override
	@Transactional
	public int save(Department department) {
		String id = department.getParentid();
		int level = departmentMapper.selectByPrimaryKey(id).getLevel();
		department.setLevel(level+1);
		int result = super.save(department);
		if(result > 0){
			refreshCache();
			refreshOrgCache();
		}
		return result;
	}

	@Override
	protected BaseMapper<Department, DepartmentSearch> getBaseMapper() {
		return departmentMapper;
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.deptment.service.DepartmentService#getOneByCode(java.lang.String)
	 */
	@Override
	public Department getOneByCode(String code) {
		DepartmentSearch search = new DepartmentSearch();
		search.createCriteria().andCodeEqualTo(code);
		
		List<Department> result= departmentMapper.selectByExample(search);
		if(result == null) return null;
		if(result.isEmpty()) return null;
		return result.get(0);
	}
	
	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.deptment.service.DepartmentService#getOneByName(java.lang.String)
	 */
	@Override
	public Department getOneByName(String name) {
		DepartmentSearch search = new DepartmentSearch();
		search.createCriteria().andNameEqualTo(name);
		List<Department> result= departmentMapper.selectByExample(search);
		if(result == null) return null;
		if(result.isEmpty()) return null;
		return result.get(0);
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.deptment.service.DepartmentService#getDeptJsonArray(java.lang.String)
	 */
	@Override
	public JsonArray getDeptJsonArray(String menuid) {
		JsonArray deptJsonArray = new JsonArray();
		//部门
		List<Department> departList = departmentAuthorityFilter.filter(findAll(),menuid);
		getDeptJsonArray(departList,deptJsonArray,menuid);
		return deptJsonArray;
	}
	
	/**
	 * 组织页面需要的树结构json字符
	 * @param deptId
	 * @return
	 */
	private void getDeptJsonArray(List<Department> departList,JsonArray deptJsonArray,String menuid) {
		if(departList ==null) return;
		for (Department department : departList) {
			JsonObject json = new JsonObject();
			json.addProperty("id",department.getId());
			json.addProperty("pId",department.getParentid());
			json.addProperty("isParent",hasChild(department.getId()));
			json.addProperty("name",department.getName());
			//add by kouyunhao 2013-12-06 添加code字段
			json.addProperty("code",department.getCode());
			if("00".equals(department.getId())){
				//json.addProperty("nocheck","true");
				//json.addProperty("icon","../../../../images/picone/home_main.png");
				json.addProperty("checked","false");
				json.addProperty("icon","/itms/images/picone/home_main.png");
			}else{
				json.addProperty("checked","false");
			}
			deptJsonArray.add(json);
		}
	}
	
	
	/***
	 * 用户的部门以及子部门
	 * @param deptid
	 * @return
	 */
	public List<Department> getAllChildDepartment(String deptid){
		// 找出当前部门的子部门
		List<Department> departs = new ArrayList<Department>();
		departs.add(getById(deptid));
		getAllChildren(departs, deptid);
		return departs;
	}
	
	
	@Override
	public List<String> getAllChildIds(String id) {
		List<Department> departs = getAllChildDepartment(id);
		List<String> ids = new ArrayList<String>();
		for (Department depart : departs) {
			if(depart!=null){
				ids.add(depart.getId());
			}
		}
		return ids;
	}
	
	

	@Override
	public String getParentIdById(String id) {
		Department depart = getById(id);
		if(depart!=null)	return	depart.getParentid();
		return null;
	}

	/***
	 * 递归查找部门的所有子部门
	 * 
	 * @param resultList
	 * @param deptid
	 */
	private void getAllChildren(List<Department> resultList, String deptid) {
		// 找出当前部门的子部门
		List<Department> departs = getChildren(deptid);
		if (departs != null && !departs.isEmpty()) {
			for (Department department : departs) {
				resultList.add(department);
				getAllChildren(resultList, department.getId());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.deptment.service.DepartmentService#getCountByLevel(int)
	 */
	@Override
	public int getCountByLevel(int level) {
		return departmentMapper.getCountByLevel(level);
	}



	@Override
	public List<Department> getParent(String menuid) {
		List<Department> departList = departmentAuthorityFilter.filterTree(findAll(),menuid);
		return departList;
	}



	@Override
	public List<Department> getDept(String menuid) {
		//部门
		List<Department> departList = departmentAuthorityFilter.filter(findAll(),menuid);
		if(!departList.isEmpty()){
			return departList;
		}else {
			return null;
		}
		
	}

	/* (non-Javadoc)
	 * @see@see com.xiangxun.atms.common.deptment.service.DepartmentService#refreshOrgCache()
	 */
	@Override
	public void refreshOrgCache() {
		List<Department> list =  findAll();
		Table<String, String, String> table = HashBasedTable.create();
		for (Department dept : list) {
			//存储的对象为table
			table.put(dept.getCode(),DepartmentService.DEPARTMENTCODE_CACHE,dept.getName());
		}
		cache.put(DepartmentService.DEPARTMENTCODE_CACHE, table);
	}
	
}
