package com.xiangxun.atms.module.property.mapper;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.property.domain.DeviceFtpInfo;
import com.xiangxun.atms.module.property.domain.DeviceFtpInfoSearch;

public interface DeviceFtpInfoMapper extends BaseMapper<DeviceFtpInfo, DeviceFtpInfoSearch> {
	
	//根据设备编码获取ftp信息
	DeviceFtpInfo getFtpByDeviceCode(String deviceCode);
	
}