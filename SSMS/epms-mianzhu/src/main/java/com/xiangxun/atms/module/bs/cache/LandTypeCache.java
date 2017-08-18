package com.xiangxun.atms.module.bs.cache;

import javax.annotation.Resource;

import com.xiangxun.atms.framework.cache.BaseCache;
import com.xiangxun.atms.framework.log.Logging;
import com.xiangxun.atms.module.bs.service.LandTypeService;

public class LandTypeCache implements BaseCache {

	private final Logging logger = new Logging(this.getClass());
	/**
	 * 根据ID获取名称
	 */
	public final static String ID_NAME = "LANDTYPE_NAME";
	/**
	 * 所有数据
	 */
	public final static String ALL_ITEM = "LANDTYPE_ALL";
	/**
	 * 根据code获取名称
	 */
	public final static String CODE_NAME ="LANDTYPE_CODENAME";
	@Resource
    LandTypeService landTypeService;

	@Override
	public String getCacheKey() {
		return DIC_CACHE;
	}

	@Override
	public void init() throws Exception {
		landTypeService.refreshCache();
		logger.info("[地块土壤类型] 缓存完成。");
	}
	
}
