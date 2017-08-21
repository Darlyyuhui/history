/**
 * 
 */
package com.xiangxun.atms.common.user.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiangxun.atms.common.system.dao.SystemResourceMapper;
import com.xiangxun.atms.common.user.dao.RoleMapper;
import com.xiangxun.atms.common.user.service.RoleService;
import com.xiangxun.atms.common.user.vo.Role;
import com.xiangxun.atms.common.user.vo.RoleSearch;
import com.xiangxun.atms.common.user.vo.RoleSearch.Criteria;
import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.util.StringUtils;

/**
 * 角色接口实现
 * @author zhouhaij
 */
@Service("roleService")
public class RoleServiceImpl extends AbstractBaseService<Role,RoleSearch> implements RoleService {

	@Resource
	RoleMapper roleMapper;
	
	@Resource
	SystemResourceMapper systemResourceMapper;
	
	/* (non-Javadoc)
	 * @see com.xiangxun.atms.framework.base.AbstractBaseService#getBaseMapper()
	 */
	@Override
	protected BaseMapper<Role,RoleSearch> getBaseMapper() {
		return roleMapper;
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.user.service.RoleService#findAll()
	 */
	@Override
	public List<Role> findAll() {
		return roleMapper.getRoleResourceContent(null);
	}

	@Override
	public List<Role> getRoleResourceContent(Role role) {
		return roleMapper.getRoleResourceContent(role);
	}

	@Override
	public Page getRolesByCondition(Map<String, Object> map, int pageNo,int pageSize, String sortType) {
		RoleSearch search = new RoleSearch();
		Criteria criteria = search.createCriteria();
		if(map!=null){
			
			if(StringUtils.notEmpty(map.get("name")+"")){
				criteria.andNameEqualTo(map.get("name").toString());
			}
		}
		if(StringUtils.notEmpty(sortType)){
			search.setOrderByClause(sortType);
		}
		Page page = selectByExample(search, pageNo, pageSize);
		return page;
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.framework.base.AbstractBaseService#deleteById(java.lang.String)
	 */
	@Override
	@Transactional
	public int deleteById(String id) {
		systemResourceMapper.deleteResourceByRoleId(id);
		return super.deleteById(id);
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.user.service.RoleService#containsName(java.lang.String)
	 */
	@Override
	public boolean containsName(String name) {
		return roleMapper.getRolesByName(name)>0;
	}

	
}
