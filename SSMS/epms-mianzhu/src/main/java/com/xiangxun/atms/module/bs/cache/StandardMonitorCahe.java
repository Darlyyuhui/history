package com.xiangxun.atms.module.bs.cache;

import javax.annotation.Resource;

import com.xiangxun.atms.framework.cache.BaseCache;
import com.xiangxun.atms.framework.log.Logging;
import com.xiangxun.atms.module.bs.service.StandardMonitorService;
public class StandardMonitorCahe implements BaseCache{
	@Resource
	StandardMonitorService StandardMonitorService;
	private final Logging logger = new Logging(this.getClass());
	/**
	 * 根据指标类型，获取监测配置项
	 */
	public final static String TYPE_ITEMS = "T_STANDARD_MONITOR_TYPEITEMS";
	/**
	 * 根据ID获取对象
	 */
	public final static String ID_OBJ = "T_STANDARD_MONITOR_IDOBJ";

	@Override
	public String getCacheKey() {
	
		return DIC_CACHE;
	}

	@Override
	public void init() throws Exception {
		StandardMonitorService.refreshCache();
		logger.info("[监测指标] 缓存完成。");
	}

}
