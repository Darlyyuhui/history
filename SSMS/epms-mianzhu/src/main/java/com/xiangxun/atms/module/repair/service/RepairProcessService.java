package com.xiangxun.atms.module.repair.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.module.repair.vo.RepairProcess;
import com.xiangxun.atms.module.repair.vo.RepairProcessSearch;

public interface RepairProcessService extends BaseService<RepairProcess, RepairProcessSearch> {
	
	void saveInfo(RepairProcess info, MultipartHttpServletRequest fileRequest);
	
	void updateInfo(RepairProcess info, MultipartHttpServletRequest fileRequest);
	
}