package com.xiangxun.atms.common.road.cache;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.xiangxun.atms.common.road.service.RoadInfoService;
import com.xiangxun.atms.common.road.vo.RoadInfo;
import com.xiangxun.atms.framework.cache.BaseCache;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.log.Logging;
/***
 * 道路信息缓存
 * @author YanTao
 * @Apr 21, 2013 2:04:23 PM
 */
@Component
public class RoadInfoCache implements BaseCache{
	
	private final Logging logger = new Logging(RoadInfoCache.class);

	@Resource
	Cache cache;

	@Resource
	RoadInfoService roadInfoService;
	
	@Override
	public String getCacheKey() {
		return ANALYZE_CACHE;
	}

	@Override
	public void init() throws Exception {
		logger.warn("[道路信息] 缓存初始化开始");
		List<RoadInfo> caches =  roadInfoService.findAll();
		Table<String, String, String> table = HashBasedTable.create();
		for (RoadInfo roadInfo : caches) {
			//存储的对象为table
			table.put(roadInfo.getId(), RoadInfo.class.getSimpleName(), roadInfo.getName());
		}
		cache.put(RoadInfo.class.getSimpleName(), table);
		logger.warn("[道路信息] 缓存初始化完成");
	}

}
