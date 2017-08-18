package com.xiangxun.atms.module.pollute.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.module.pollute.vo.ReservePlan;
import com.xiangxun.atms.module.pollute.vo.ReservePlanSearch;

public interface ReservePlanService extends BaseService<ReservePlan, ReservePlanSearch> {
	
	void saveInfo(ReservePlan info, MultipartHttpServletRequest fileRequest);
	
	void updateInfo(ReservePlan info, MultipartHttpServletRequest fileRequest);
	
}