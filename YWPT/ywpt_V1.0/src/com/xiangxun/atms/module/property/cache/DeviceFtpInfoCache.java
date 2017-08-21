package com.xiangxun.atms.module.property.cache;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.xiangxun.atms.framework.cache.BaseCache;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.log.Logging;
import com.xiangxun.atms.module.property.domain.DeviceFtpInfo;
import com.xiangxun.atms.module.property.service.DeviceFtpInfoService;
/***
 * FTP 信息缓存
 * @author YanTao
 * @Apr 21, 2013 2:04:23 PM
 */
@Component
public class DeviceFtpInfoCache implements BaseCache{

	private final Logging logger = new Logging(DeviceFtpInfoCache.class);
	
	@Resource
	Cache cache;

	@Resource
	DeviceFtpInfoService ftpService;
	
	@Override
	public String getCacheKey() {
		return ANALYZE_CACHE;
	}

	@Override
	public void init() throws Exception {
		logger.info("[FTP信息] 缓存初始化开始");
		List<DeviceFtpInfo> caches =  ftpService.findAll();
		Table<String, String, String> table = HashBasedTable.create();
		for (DeviceFtpInfo ftpInfo : caches) {
			//存储的对象为table
			table.put(ftpInfo.getId(), DeviceFtpInfo.class.getSimpleName(), ftpInfo.getName()+" [ "+ftpInfo.getFtpip()+" ] ");
		}
		cache.put(DeviceFtpInfo.class.getSimpleName(), table);
		logger.info("[FTP信息] 缓存初始化完成");
	}

}
