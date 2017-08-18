package com.xiangxun.atms.module.apb.cache;

import javax.annotation.Resource;

import com.xiangxun.atms.framework.cache.BaseCache;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.log.Logging;
import com.xiangxun.atms.module.apb.service.ApbProductTypeService;
public class ApbProductTypeCache implements BaseCache{
	private Logging logger = new Logging(this.getClass());
    @Resource
    ApbProductTypeService apbProductTypeService;
    @Resource
    Cache cache;
    /**
     * 根据ID获得名称
     */
    public final static String ID_NAME = "APBPRODUCTTYPE_ID_NAME";
    /**
     * 根据CODE获得名称
     */
    public final static String CODE_NAME = "APBPRODUCTTYPE_CODE_NAME";
    /**
	 * 所有数据
	 */
	public final static String ALL_ITEM = "APBPRODUCTTYPE_ALL";
	@Override
	public String getCacheKey() {
		
		return DIC_CACHE;
	}

	@Override
	public void init() throws Exception {
		apbProductTypeService.refreshCache();
		
		logger.info("[产品种类]缓存完成");
		
	}

}
