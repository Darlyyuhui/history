package com.xiangxun.atms.module.property.service.impl;



import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.property.domain.DeviceInfo;
import com.xiangxun.atms.module.property.domain.DeviceType;
import com.xiangxun.atms.module.property.domain.DeviceTypeInfo;
import com.xiangxun.atms.module.property.domain.DeviceTypeSearch;
import com.xiangxun.atms.module.property.mapper.DeviceTypeMapper;
import com.xiangxun.atms.module.property.service.DeviceTypeService;


/***
 * 设备和类型关联表 服务接口类
 * @author yantao
 */

@Service("deviceTypeService")
public class DeviceTypeServiceImpl extends AbstractBaseService<DeviceType, DeviceTypeSearch> implements DeviceTypeService {

	@Resource
	DeviceTypeMapper deviceTypeMapper;
	
	@Override
	protected BaseMapper<DeviceType, DeviceTypeSearch> getBaseMapper() {
		return deviceTypeMapper;
	}

	@Override
	public List<DeviceInfo> getDeviceByTypeId(String devicetypeId) {
		return deviceTypeMapper.getDeviceByTypeId(devicetypeId);
	}

	@Override
	public List<DeviceTypeInfo> getTypeByDeviceId(String deviceId) {
		return deviceTypeMapper.getTypeByDeviceId(deviceId);
	}

	@Override
	public Map<String, String> staticByType() {		
		return deviceTypeMapper.staticByType();
	}
	
}
