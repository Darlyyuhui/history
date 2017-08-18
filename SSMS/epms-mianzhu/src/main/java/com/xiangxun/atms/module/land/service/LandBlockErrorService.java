package com.xiangxun.atms.module.land.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.module.land.vo.LandBlockError;
import com.xiangxun.atms.module.land.vo.LandBlockErrorSearch;

public interface LandBlockErrorService extends BaseService<LandBlockError, LandBlockErrorSearch> {
	
	void saveInfo(LandBlockError info, MultipartHttpServletRequest fileRequest);
	
	void updateInfo(LandBlockError info, MultipartHttpServletRequest fileRequest);
}