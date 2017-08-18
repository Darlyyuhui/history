package com.xiangxun.epms.mobile.business.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiangxun.epms.mobile.business.dao.MobileAppMapper;
import com.xiangxun.epms.mobile.business.domain.MobileApp;
import com.xiangxun.epms.mobile.business.service.MobileAppService;
import com.xiangxun.epms.mobile.config.parameter.CustomParameters;

@Service
public class MobileAppServiceImpl  implements MobileAppService {
    @Resource
    private MobileAppMapper mobileAppMapper;
    @Resource
	CustomParameters custom;
	@Override
	public MobileApp getNewVersion() {
		MobileApp info = mobileAppMapper.getNewVersion();
		if (info == null) {
			return new MobileApp();
		}
		info.setSaveUrl(custom.getWebUrl()+"/"+info.getSaveUrl());
		return info;
	}

}