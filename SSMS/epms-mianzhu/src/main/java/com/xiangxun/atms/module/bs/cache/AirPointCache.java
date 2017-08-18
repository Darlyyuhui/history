package com.xiangxun.atms.module.bs.cache;

import javax.annotation.Resource;

import com.xiangxun.atms.framework.cache.BaseCache;
import com.xiangxun.atms.framework.log.Logging;
import com.xiangxun.atms.module.bs.service.AirPointService;

public class AirPointCache implements BaseCache {

	private final Logging logger = new Logging(this.getClass());
	/**
	 * 根据ID获取名称
	 */
	public final static String ID_NAME = "AIRPOINT_ID_NAME";
	/**
	 * 根据ID获取地址
	 */
	public final static String ID_LOCATION = "AIRPOINT_ID_LOCATION";
	/**
	 * 根据ID获取对象信息
	 */
	public final static String ID_OBJ = "AIRPOINT_ID_OBJECT";
	/**
	 * 所有数据
	 */
	public final static String ALL_ITEM = "AIRPOINT_ALL";
	
	@Resource
	AirPointService airPointService;

	@Override
	public String getCacheKey() {
		return DIC_CACHE;
	}

	@Override
	public void init() throws Exception {
		airPointService.refreshCache();
		logger.info("[大气点位] 缓存完成。");
	}
	
}
