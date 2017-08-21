package com.xiangxun.atms.common.user.service;

/**
 * <p>模块验证器</p>
 * @author zhouhaij
 * @since 1.0
 * @version
 */
public interface ModuleValidator {

	/***
	 * 根据menuid验证是不是某个模块
	 * @param menuid
	 * @param status 默认是false
	 * @return
	 */
	public boolean isDeviceCode(String menuid,boolean status);	
	
	/***
	 * 该模块是否可用
	 * @return
	 */
	public boolean isEnable(String menuid);
	
}
