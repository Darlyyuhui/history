package com.xiangxun.atms.module.sergrade.cache;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.xiangxun.atms.framework.cache.BaseCache;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.log.Logging;
import com.xiangxun.atms.module.sergrade.domain.FactoryInfo;
import com.xiangxun.atms.module.sergrade.service.FactoryInfoService;
/***
 * 服务厂商 信息缓存
 * @author kouyunhao
 * 
 */
@Component
public class FactoryInfoCache implements BaseCache{

	private final Logging logger = new Logging(FactoryInfoCache.class);
	
	@Resource
	Cache cache;

	@Resource
	FactoryInfoService factoryInfoService;
	
	@Override
	public String getCacheKey() {
		return ANALYZE_CACHE;
	}
 
	@Override
	public void init() throws Exception {
		logger.info("[服务厂商信息] 缓存初始化开始");
		List<FactoryInfo> caches =  factoryInfoService.findAll();
		Table<String, String, String> table = HashBasedTable.create();
		for (FactoryInfo factoryInfo : caches) {
			//存储的对象为table
			table.put(factoryInfo.getId(), FactoryInfo.class.getSimpleName(), factoryInfo.getName());
		}
		cache.put(FactoryInfo.class.getSimpleName(), table);
		logger.info("[服务厂商信息] 缓存初始化完成");
	}

}
