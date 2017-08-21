package com.xiangxun.atms.module.property.service;

import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.module.property.domain.DeviceInfoHis;
import com.xiangxun.atms.module.property.domain.DeviceInfoHisSearch;

public interface DeviceInfoHisService extends BaseService<DeviceInfoHis, DeviceInfoHisSearch> {
	
	public void updateAfterRecordId(String id, String afterId);
	
	boolean hasRecordModified(String id);

}
