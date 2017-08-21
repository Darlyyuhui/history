package com.xiangxun.atms.module.property.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xiangxun.atms.framework.cache.BaseCache;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.log.Logging;
import com.xiangxun.atms.module.property.domain.DeviceInfo;
import com.xiangxun.atms.module.property.domain.DeviceFtpInfo;
import com.xiangxun.atms.module.property.service.DeviceInfoService;
import com.xiangxun.atms.module.property.service.DeviceFtpInfoService;

/**
 * <p>缓存设备编号的ftp信息</p>
 * @author zhouhaij
 * @since 1.0
 * @version
 */
public class DeviceCodeFtpCache  implements BaseCache{
	
	public static final String DEVICE_FTP="deviceftp";
	
	private final Logging logger = new Logging(DeviceCodeFtpCache.class);

	@Resource
	Cache cache;
	
	@Resource
	DeviceFtpInfoService deviceFtpInfoService;
	
	@Resource
	DeviceInfoService deviceInfoService;
	
	/* (non-Javadoc)
	 * @see com.xiangxun.atms.framework.cache.BaseCache#init()
	 */
	@Override
	public void init() throws Exception {
		logger.info("[FTP与设备编号关系] 缓存初始化开始");
		List<DeviceInfo> devices = deviceInfoService.findAll();
		Map<String,DeviceFtpInfo> maps = new HashMap<String,DeviceFtpInfo>();
		for (DeviceInfo deviceInfo : devices) {
			DeviceFtpInfo ftpInfo = deviceFtpInfoService.getFtpByDeviceCode(deviceInfo.getCode());
			maps.put(deviceInfo.getCode(), ftpInfo);
		}
		cache.put(DEVICE_FTP, maps);
		logger.info("[FTP与设备编号关系] 缓存初始化完成");
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.framework.cache.BaseCache#getCacheKey()
	 */
	@Override
	public String getCacheKey() {
		return ANALYZE_CACHE;
	}

}
