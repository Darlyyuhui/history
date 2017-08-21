package com.xiangxun.atms.module.property.mapper;

import java.util.List;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.property.domain.DeviceDirectSpeed;
import com.xiangxun.atms.module.property.domain.DeviceDirectSpeedSearch;


public interface DeviceDirectSpeedMapper extends BaseMapper<DeviceDirectSpeed,DeviceDirectSpeedSearch> {
	
    List<DeviceDirectSpeed> getDeviceDirectSpeedByDeviceCode(String deviceCode);
    
    void deleteByDeviceCode(String deviceCode);
    
}