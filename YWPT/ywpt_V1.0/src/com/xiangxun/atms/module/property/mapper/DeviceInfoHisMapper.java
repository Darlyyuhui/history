package com.xiangxun.atms.module.property.mapper;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.property.domain.DeviceInfoHis;
import com.xiangxun.atms.module.property.domain.DeviceInfoHisSearch;

public interface DeviceInfoHisMapper extends BaseMapper<DeviceInfoHis, DeviceInfoHisSearch> {
	
	int countRecordInHistory(String id);
	
}