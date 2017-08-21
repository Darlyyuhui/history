package com.xiangxun.atms.module.property.cache;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.xiangxun.atms.framework.cache.BaseCache;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.log.Logging;
import com.xiangxun.atms.module.property.domain.DeviceTypeInfo;
import com.xiangxun.atms.module.property.service.DeviceTypeInfoService;
/***
 * 设备功能 信息缓存
 * @author YanTao
 * @Apr 21, 2013 2:04:23 PM
 */
@Component
public class DeviceTypeInfoCache implements BaseCache{
	

	private final Logging logger = new Logging(DeviceTypeInfoCache.class);
	
	@Resource
	Cache cache;

	@Resource
	DeviceTypeInfoService deviceTypeInfoService;
	
	@Override
	public String getCacheKey(){
		return ANALYZE_CACHE;
	}

	@Override
	public void init() throws Exception {
		logger.warn("[设备功能] 缓存初始化开始");
		List<DeviceTypeInfo> caches =  deviceTypeInfoService.findAll();
		Table<String, String, String> table = HashBasedTable.create();
		for (DeviceTypeInfo deviceTypeInfo : caches) {
			//存储的对象为table
			table.put(deviceTypeInfo.getId(), DeviceTypeInfo.class.getSimpleName(), deviceTypeInfo.getName());
		}
		cache.put(DeviceTypeInfo.class.getSimpleName(), table);
		logger.warn("[设备功能] 缓存初始化完成");
	}

}
