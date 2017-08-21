package com.xiangxun.atms.module.property.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.property.domain.DeviceInfoHis;
import com.xiangxun.atms.module.property.domain.DeviceInfoHisSearch;
import com.xiangxun.atms.module.property.mapper.DeviceInfoHisMapper;
import com.xiangxun.atms.module.property.service.DeviceInfoHisService;

@Service("deviceInfoHisService")
public class DeviceInfoHisServiceImpl extends AbstractBaseService<DeviceInfoHis, DeviceInfoHisSearch> implements DeviceInfoHisService {
	
	@Resource
	DeviceInfoHisMapper deviceInfoHisMapper;

	@Override
	protected BaseMapper<DeviceInfoHis, DeviceInfoHisSearch> getBaseMapper() {
		return deviceInfoHisMapper;
	}

	@Override
	public void updateAfterRecordId(String id, String afterId) {
		DeviceInfoHis deviceHis = super.getById(id);
		deviceHis.setAfterRecordId(afterId);
		deviceInfoHisMapper.updateByPrimaryKeySelective(deviceHis);
	}

	@Override
	public boolean hasRecordModified(String id) {
		int count = deviceInfoHisMapper.countRecordInHistory(id);
		if(count == 0){
			return false;
		}else{
			return true;
		}
	}

}
