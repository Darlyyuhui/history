package com.xiangxun.atms.module.bs.service;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.module.bs.vo.AirPoint;
import com.xiangxun.atms.module.bs.vo.AirPointSearch;

public interface AirPointService extends BaseService<AirPoint, AirPointSearch> {
	
	void refreshCache();
}