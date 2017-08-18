package com.xiangxun.atms.module.bs.service;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.module.bs.vo.Owner;
import com.xiangxun.atms.module.bs.vo.OwnerSearch;

public interface OwnerService extends BaseService<Owner, OwnerSearch> {
	/**
	 * 刷新缓存
	 */
	void refreshCache();
}