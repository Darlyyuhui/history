package com.xiangxun.atms.module.apb.cache;

import javax.annotation.Resource;

import com.xiangxun.atms.framework.cache.BaseCache;

import com.xiangxun.atms.framework.log.Logging;
import com.xiangxun.atms.module.apb.service.ApbProductService;



public class ApbProductCache implements BaseCache{
	private Logging logger = new Logging(this.getClass());
	@Resource
	 ApbProductService apbProducrService;
	/**
	 * 根据ID获得name
	 */
	public final static String ID_NAME = "APBPRODUCT_NAME";

	@Override
	public String getCacheKey() {
		
		return null;
	}

	@Override
	public void init() throws Exception {
		apbProducrService.refreshCache();
		logger.info("[农产品基地产品]缓存完成");
	}

}
