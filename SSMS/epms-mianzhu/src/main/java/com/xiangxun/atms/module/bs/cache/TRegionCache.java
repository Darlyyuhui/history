package com.xiangxun.atms.module.bs.cache;

import javax.annotation.Resource;

import com.xiangxun.atms.framework.cache.BaseCache;
import com.xiangxun.atms.framework.log.Logging;
import com.xiangxun.atms.module.bs.service.TRegionService;

public class TRegionCache implements BaseCache {

	private final Logging logger = new Logging(this.getClass());
	/**
	 * 根据ID获取名称
	 */
	public final static String ID_NAME = "TREGION_NAME";
	/**
	 * 根据ID获取全名称
	 */
    public final static String ID_FULLNAME = "TREGION_FULL_NAME";
    /**
     * 根据ID获取坐标值：经度,纬度
     */
    public final static String ID_LOCATION = "TREGION_LOCATION";
    /**
     * 根据ID获取对象
     */
    public final static String ID_OBJ = "TREGION_OBJ";
    /**
     * 所有数据
     */
    public final static String ALL_ITEM = "TREGION_ALL";
	
	@Resource
    TRegionService regionService;
	
	@Override
	public String getCacheKey() {
		return DIC_CACHE;
	}

	@Override
	public void init() throws Exception {
		regionService.refreshCache();
        logger.info("[行政区域] 缓存初始化完成");
	}

}
