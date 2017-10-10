package com.xiangxun.atms.module.bs.cache;

import javax.annotation.Resource;

import com.xiangxun.atms.framework.cache.BaseCache;
import com.xiangxun.atms.framework.log.Logging;
import com.xiangxun.atms.module.bs.service.RepairStageService;

public class RepairStageCache implements BaseCache {
	
	private final Logging logger = new Logging(this.getClass());

	@Resource
	RepairStageService repairStageService;
	
	/**
	 * 所有数据
	 */
	public final static String ALL_ITEM = "REPAIRSTAGE_ALL";
	/**
	 * 根据code获取名称
	 */
	public final static String ID_NAME = "REPAIRSTAGE_IDNAME";
	
	/**
	 * 一级阶段集合
	 */
	public final static String TOP_LIST = "REPAIRSTAGE_TOPLIST";
	/**
	 * 除过一级阶段的集合
	 */
	public final static String STAGE_MAP = "REPAIRSTAGE_STAGEMAP"; 

	@Override
	public String getCacheKey() {
		return DIC_CACHE;
	}

	@Override
	public void init() throws Exception {
		repairStageService.refreshCache();
		logger.info("[土壤修复阶段] 缓存完成。");
	}
	
}
