package com.xiangxun.atms.module.property.service;

import java.util.List;

import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.module.property.domain.DeviceTypeInfo;
import com.xiangxun.atms.module.property.domain.DeviceTypeInfoSearch;

/**
 * 设备类型 服务接口
 * @author YanTao
 * @Apr 19, 2013 2:07:20 PM
 */
public interface DeviceTypeInfoService extends BaseService<DeviceTypeInfo,DeviceTypeInfoSearch>{
	
	/***
	 * 获取所有的设备类型
	 * @return
	 */
	public List<DeviceTypeInfo> findAll();
	
	/***
	 * 根据id获取所有的子节点
	 * @param id
	 * @return
	 */
	public List<DeviceTypeInfo> getChildren(String id);
	
	/***
	 * 判断是否有子节点
	 * @param id
	 * @return
	 */
	public boolean hasChild(String id);
	

}
