package com.xiangxun.atms.module.apb.cache;

import javax.annotation.Resource;

import com.xiangxun.atms.framework.cache.BaseCache;
import com.xiangxun.atms.framework.log.Logging;
import com.xiangxun.atms.module.apb.service.ApbInfoService;

public class ApbInfoCache implements BaseCache{
	private final Logging logger = new Logging(this.getClass());
	@Resource
	ApbInfoService apbInfoService;
	/**
	 * 根据ID获取名称
	 */
	public final static String ID_NAME = "APBINFO_NAME";
	/**
	 * 根据ID获取主营名称
	 */
	public final static String ID_MAINPRODUCT = "APBINFO_MAINPRODUCT";
    /**
     * 获取所有APBINFO信息
     */
	public final static String ALL_ITEM="APBINFO_ALL";
	@Override
	public String getCacheKey() {
		
		return DIC_CACHE;
	}

	@Override
	public void init() throws Exception {
		apbInfoService.refreshCache();
		logger.info("[农产品基地基本信息] 缓存完成。");
		
	}

}
