package com.xiangxun.atms.common.user.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.common.user.service.AuthorityFilterContext;
import com.xiangxun.atms.common.user.service.DepartmentAuthorityFilter;
import com.xiangxun.atms.common.user.service.DeviceCodeAuthorityFilter;
import com.xiangxun.atms.module.property.domain.DeviceInfo;

/**
 * <p>部门权限过滤器实现类</p>
 * @author zhouhaij
 * @since 1.0
 * @version
 */
@Service(value="authorityFilterContext")
public class AuthorFilterContextImpl implements AuthorityFilterContext {
	
	@Resource
	DepartmentAuthorityFilter departmentAuthorityFilter;
	
	@Resource
	DeviceCodeAuthorityFilter deviceCodeAuthorityFilter;

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.user.service.AuthorityFilterContext#filter(java.util.Map, java.lang.String)
	 */
	@Override
	public Map<String, Object> filter(Map<String, Object> map, String menuid) {
		//判断是否是deivceCode的业务模块
		if(deviceCodeAuthorityFilter.hasDeviceCode(menuid)){
			return deviceCodeAuthorityFilter.filter(map, menuid);
		}else{
			//如果不是各个业务模块
			return departmentAuthorityFilter.filter(map, menuid);
		}
	}

	/**
	 * 权限处理返回sql字符串
	 */
	@Override
	public String filterSql(String menuid) {
		//判断是否是deivceCode的业务模块
		if(deviceCodeAuthorityFilter.hasDeviceCode(menuid)){
			return deviceCodeAuthorityFilter.filterSql(menuid);
		}else{
			//如果不是各个业务模块
			return departmentAuthorityFilter.filterSql(menuid);
		}
	}

	@Override
	public List<DeviceInfo> filterDialog(Map<String, Object> map,
			String menuid) {
		//判断是否是deivceCode的业务模块
		if(deviceCodeAuthorityFilter.hasDeviceCode(menuid)){
			return deviceCodeAuthorityFilter.filterDialog(map, menuid);
		}else{
			//如果不是各个业务模块
			return departmentAuthorityFilter.filterDialog(map, menuid);
		}
	}

}
