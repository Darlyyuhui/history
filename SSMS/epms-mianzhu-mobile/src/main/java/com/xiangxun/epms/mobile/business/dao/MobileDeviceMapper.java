package com.xiangxun.epms.mobile.business.dao;


import java.util.List;

import com.xiangxun.epms.mobile.business.domain.MobileDevice;


public interface MobileDeviceMapper {
	List<MobileDevice> findByImei(String imeiNo);
}