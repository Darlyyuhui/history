package com.xiangxun.atms.module.land.cache;

import javax.annotation.Resource;

import com.xiangxun.atms.framework.cache.BaseCache;
import com.xiangxun.atms.framework.log.Logging;
import com.xiangxun.atms.module.land.service.LandBlockService;

public class LandBlockCache implements BaseCache {

	private final Logging logger = new Logging(this.getClass());
	/**
	 * 根据ID获取地块信息
	 */
	public final static String LB_ID_OBJ = "LANDBLOCK_ID_ITEM";
	/**
	 * 根据ID获取地块名称和编号信息
	 */
	public final static String LB_ID_CODENAME = "LANDBLOCK_ID_CODENAME";
	/**
	 * 根据ID获取地块名称
	 */
	public final static String LB_ID_NAME = "LANDBLOCK_ID_NAME";
	/**
	 * 根据ID获取地块编号
	 */
	public final static String LB_ID_CODE = "LANDBLOCK_ID_CODE";
    /**
     * 所有数据
     */
    public final static String LB_ALL = "LANDBLOCK_ALL";
	
    @Resource
    LandBlockService landBlockService;
    
	@Override
	public String getCacheKey() {
		return DIC_CACHE;
	}

	@Override
	public void init() throws Exception {
		landBlockService.refreshCache();
		logger.info("[土壤地块] 缓存初始化完成");
	}

}
