package com.xiangxun.atms.module.reg.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.module.reg.vo.WaterPollute;
import com.xiangxun.atms.module.reg.vo.WaterPolluteSearch;

public interface WaterPolluteService extends BaseService<WaterPollute, WaterPolluteSearch> {
	
	void saveInfo(WaterPollute info, MultipartHttpServletRequest fileRequest);
	
	void updateInfo(WaterPollute info, MultipartHttpServletRequest fileRequest);
}