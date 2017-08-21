package com.xiangxun.atms.common.user.service;

/**
 * <p>业务数据的数据权限过滤器</p>
 * <p>与部门的过滤器的区别在于，业务数据中表里不存在org字段，只有对应的deviceCode</p>
 * @author zhouhaij
 * @since 1.0
 * @version
 */
public interface DeviceCodeAuthorityFilter extends AuthorityFilterContext{
	
	/***
	 * 判断是否是含有deviceCode字段的业务模块
	 * @param menuid
	 * @return
	 */
	public boolean hasDeviceCode(String menuid);
	
	/***
	 * 该模块的业务功能是否已开启
	 * @param menuid
	 * @return
	 */
	public boolean isEnable(String menuid);
	
}
