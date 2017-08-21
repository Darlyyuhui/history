package com.xiangxun.atms.module.property.mapper;

import java.util.List;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.property.domain.DeviceTypeInfo;
import com.xiangxun.atms.module.property.domain.DeviceTypeInfoSearch;


public interface DeviceTypeInfoMapper extends BaseMapper<DeviceTypeInfo,DeviceTypeInfoSearch> {
	/***
	 * 根据id获取所有的子节点
	 * @param id
	 * @return
	 */
	public List<DeviceTypeInfo> getLeafNodeById(String id);
	
	/***
	 * 判断是否有子节点
	 * @param id
	 * @return
	 */
	public int hasChild(String id);
}