package com.xiangxun.atms.module.property.cache;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.xiangxun.atms.framework.cache.BaseCache;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.log.Logging;
import com.xiangxun.atms.module.property.domain.DeviceCompanyInfo;
import com.xiangxun.atms.module.property.service.DeviceCompanyInfoService;
/***
 * 设备生产厂商 信息缓存
 * @author YanTao
 * @Apr 21, 2013 2:04:23 PM
 */
@Component
public class DeviceCompanyInfoCache implements BaseCache{

	private final Logging logger = new Logging(DeviceCompanyInfoCache.class);
	
	@Resource
	Cache cache;

	@Resource
	DeviceCompanyInfoService deviceCompanyInfoService;
	
	@Override
	public String getCacheKey() {
		return DIC_CACHE;
	}

	@Override
	public void init() throws Exception {
		logger.info("[设备厂商] 缓存初始化开始");
		List<DeviceCompanyInfo> caches =  deviceCompanyInfoService.findAll();
		Table<String, String, String> table = HashBasedTable.create();
		for (DeviceCompanyInfo deviceCompanyInfo : caches) {
			//存储的对象为table
			table.put(deviceCompanyInfo.getId(), DeviceCompanyInfo.class.getSimpleName(), deviceCompanyInfo.getName());
		}
		cache.put(DeviceCompanyInfo.class.getSimpleName(), table);
		logger.info("[设备厂商] 缓存初始化完成");
	}

}
