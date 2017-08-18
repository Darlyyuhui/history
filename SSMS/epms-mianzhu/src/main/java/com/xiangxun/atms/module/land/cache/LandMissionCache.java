package com.xiangxun.atms.module.land.cache;

import javax.annotation.Resource;

import com.xiangxun.atms.framework.cache.BaseCache;
import com.xiangxun.atms.framework.log.Logging;
import com.xiangxun.atms.module.land.service.LandMissionService;

public class LandMissionCache implements BaseCache {

	private final Logging logger = new Logging(this.getClass());

	@Resource
	LandMissionService landMissionService;
	
	/**
	 * 根据ID获取任务名称
	 */
	public final static String LM_ID_NAME = "LANDMISSION_ID_NAME";
	/**
	 * 根据ID获取任务编号
	 */
	public final static String LM_ID_CODE = "LANDMISSION_ID_CODE";
    /**
     * 根据ID获取任务名称【编号】
     */
    public final static String LM_ID_NAMECODE = "LANDMISSION_ID_NAMECODE";
	
	@Override
	public String getCacheKey() {
		return DIC_CACHE;
	}

	@Override
	public void init() throws Exception {
		landMissionService.refreshCache();
		logger.info("[外业任务] 缓存初始化完成");
	}
	
}
