package com.xiangxun.epms.mobile.business.service;

import java.util.List;

import com.xiangxun.epms.mobile.business.domain.VideoDevice;

public interface VideoDeviceService {
	/**
	 * 查询样地视屏全部列表
	 */
	List<VideoDevice> findAll();
}
