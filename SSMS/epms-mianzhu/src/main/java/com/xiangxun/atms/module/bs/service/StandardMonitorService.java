package com.xiangxun.atms.module.bs.service;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.module.bs.vo.StandardMonitor;
import com.xiangxun.atms.module.bs.vo.StandardMonitorSearch;

public interface StandardMonitorService extends BaseService<StandardMonitor, StandardMonitorSearch> {
	/**
	 * 刷新缓存
	 */
	void refreshCache();
}