package com.xiangxun.atms.module.reg.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.module.reg.vo.WaterReg;
import com.xiangxun.atms.module.reg.vo.WaterRegSearch;

public interface WaterRegService extends BaseService<WaterReg, WaterRegSearch> {
	
	void checkById(String id, int status, String checkUser);
	
	void saveInfo(WaterReg info, MultipartHttpServletRequest fileRequest);
	
	void updateInfo(WaterReg info, MultipartHttpServletRequest fileRequest);
}