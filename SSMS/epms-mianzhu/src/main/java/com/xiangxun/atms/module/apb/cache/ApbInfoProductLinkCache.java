package com.xiangxun.atms.module.apb.cache;

import javax.annotation.Resource;

import com.xiangxun.atms.framework.cache.BaseCache;
import com.xiangxun.atms.framework.log.Logging;
import com.xiangxun.atms.module.apb.service.ApbInfoProductLinkService;

public class ApbInfoProductLinkCache implements BaseCache{
	/**
	 * 根据基地ID对应产品产品ID
	 */
	public final static String ID_PRODUCTID = "APBINFOPRODUCTLINK_PRODUCTID";
	/**
	 * 根据产品产品ID对应基地名称
	 */
	public final static String ID_INFONAME="APBINFOPRODUCTLINK_INFONAME";
	/**
	 * 根据产品产品ID对应基地
	 */
	public final static String ID_APBINFO="APBINFOPRODUCTLINK_INFO";
	/**
	 * 根据基地id对应产品类型
	 */
	public final static String ID_APBPRODUCTTYPE="APBINFOPRODUCTLINK_APBPRODUCTTYPE";
	/**
	 * 根据基地id对应产品类型名称
	 */
	public final static String ID_APBPRODUCTTYPENAME="APBINFOPRODUCTLINK_APBPRODUCTTYPENAME";
	/**
	 * 所有基地id相关产品名称
	 */
	public final static String ID_ALLAPBPRODUCTTYPENAME="APBINFOPRODUCTLINK_ALLAPBPRODUCTTYPE";
	private final Logging logger = new Logging(this.getClass());
	@Resource
	ApbInfoProductLinkService apbInfoProductLinkService;
	@Override
	public String getCacheKey() {
		
		return DIC_CACHE;
	}

	@Override
	public void init() throws Exception {
		apbInfoProductLinkService.refreshCache();
		logger.info("[基地产品对应关系] 缓存完成");
		
	}

}
