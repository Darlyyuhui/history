package com.xiangxun.atms.module.check.cache;

import javax.annotation.Resource;

import com.xiangxun.atms.framework.cache.BaseCache;
import com.xiangxun.atms.framework.log.Logging;
import com.xiangxun.atms.module.check.service.DataCheckRuleService;

public class DataCheckRuleCache implements BaseCache {

	private final Logging logger = new Logging(this.getClass());
	
	@Resource
	DataCheckRuleService dataCheckRuleService;
	
	public static final String TYPE_ITEMS = "RULETYPE_ITEMS";
	
	@Override
	public String getCacheKey() {
		return DIC_CACHE;
	}

	@Override
	public void init() throws Exception {
		dataCheckRuleService.refreshCache();
		logger.info("[数据校验规则] 缓存完成。");
	}

}
