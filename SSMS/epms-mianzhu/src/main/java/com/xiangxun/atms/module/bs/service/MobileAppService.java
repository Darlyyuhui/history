package com.xiangxun.atms.module.bs.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.module.bs.vo.MobileApp;
import com.xiangxun.atms.module.bs.vo.MobileAppSearch;

public interface MobileAppService extends BaseService<MobileApp, MobileAppSearch> {
	
	void saveInfo(MobileApp info, MultipartHttpServletRequest fileRequest);
}