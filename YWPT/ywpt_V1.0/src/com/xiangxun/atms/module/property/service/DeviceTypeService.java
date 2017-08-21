package com.xiangxun.atms.module.property.service;


import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.module.property.domain.DeviceInfo;
import com.xiangxun.atms.module.property.domain.DeviceType;
import com.xiangxun.atms.module.property.domain.DeviceTypeInfo;
import com.xiangxun.atms.module.property.domain.DeviceTypeSearch;

/***
 * 设备和类型关联表 服务接口类
 * 
 * @author yantao
 */
public interface DeviceTypeService extends BaseService<DeviceType, DeviceTypeSearch> {

	public List<DeviceInfo> getDeviceByTypeId(String devicetypeId);

	public List<DeviceTypeInfo> getTypeByDeviceId(String deviceId);
	
	Map<String,String>  staticByType();
	
}
