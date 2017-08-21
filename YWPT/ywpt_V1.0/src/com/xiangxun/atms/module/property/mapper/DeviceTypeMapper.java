package com.xiangxun.atms.module.property.mapper;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.property.domain.DeviceInfo;
import com.xiangxun.atms.module.property.domain.DeviceType;
import com.xiangxun.atms.module.property.domain.DeviceTypeInfo;
import com.xiangxun.atms.module.property.domain.DeviceTypeSearch;

public interface DeviceTypeMapper extends BaseMapper<DeviceType,DeviceTypeSearch>{
	
	List<DeviceInfo> getDeviceByTypeId(String devicetypeId);	
	List<DeviceTypeInfo> getTypeByDeviceId(String deviceId);
    Map<String,String>  staticByType();
   
}