package com.xiangxun.atms.module.bs.cache;

import javax.annotation.Resource;

import com.xiangxun.atms.framework.cache.BaseCache;
import com.xiangxun.atms.framework.log.Logging;
import com.xiangxun.atms.module.bs.service.OwnerService;

public class OwnerCahe implements BaseCache{
	private final Logging logger = new Logging(this.getClass());
	@Resource
	OwnerService ownerService;
	/**
	 * 根据ID获取名称
	 */
	public final static String ID_NAME = "T_OWNER";
	
	public final static String ALL_ITEM = "T_OWNER_ALL";
	
	@Override
	public String getCacheKey() {
		return DIC_CACHE;
	}

	@Override
	public void init() throws Exception {
		ownerService.refreshCache();
		logger.info("[村民所有人] 缓存完成。");
	}

}
