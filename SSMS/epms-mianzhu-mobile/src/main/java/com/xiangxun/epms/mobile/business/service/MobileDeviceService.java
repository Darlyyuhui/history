package com.xiangxun.epms.mobile.business.service;

import com.xiangxun.epms.mobile.business.domain.MobileDevice;

public interface MobileDeviceService {
	/**
	 * 根据设备编码获取信息
	 */
	MobileDevice findByImei(String imeiNo);

}
