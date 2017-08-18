package com.xiangxun.atms.module.repair.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.module.repair.vo.RepairCapital;
import com.xiangxun.atms.module.repair.vo.RepairCapitalSearch;

public interface RepairCapitalService extends BaseService<RepairCapital, RepairCapitalSearch> {
	
	void saveInfo(RepairCapital info, MultipartHttpServletRequest fileRequest);
	
	void updateInfo(RepairCapital info, MultipartHttpServletRequest fileRequest);
}