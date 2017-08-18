package com.xiangxun.atms.module.pollute.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.module.pollute.vo.ControllPlan;
import com.xiangxun.atms.module.pollute.vo.ControllPlanSearch;

public interface ControllPlanService extends BaseService<ControllPlan, ControllPlanSearch> {
	
	void saveInfo(ControllPlan info, MultipartHttpServletRequest fileRequest);
	
	void updateInfo(ControllPlan info, MultipartHttpServletRequest fileRequest);
}