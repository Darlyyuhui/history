package com.xiangxun.atms.module.reg.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.module.reg.vo.FarmReg;
import com.xiangxun.atms.module.reg.vo.FarmRegSearch;

public interface FarmRegService extends BaseService<FarmReg, FarmRegSearch> {
	
	void checkById(String id, int status, String checkUser);
	
	void saveInfo(FarmReg info, MultipartHttpServletRequest fileRequest);
	
	void updateInfo(FarmReg info, MultipartHttpServletRequest fileRequest);
}