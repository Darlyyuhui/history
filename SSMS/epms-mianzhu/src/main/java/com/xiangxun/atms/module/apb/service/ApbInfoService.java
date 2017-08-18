package com.xiangxun.atms.module.apb.service;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.module.apb.vo.ApbInfo;
import com.xiangxun.atms.module.apb.vo.ApbInfoSearch;

public interface ApbInfoService extends BaseService<ApbInfo, ApbInfoSearch> {
	/**
	 * 刷新缓存
	 */
	void refreshCache();
}