package com.xiangxun.atms.module.reg.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.module.reg.vo.ManureReg;
import com.xiangxun.atms.module.reg.vo.ManureRegSearch;

public interface ManureRegService extends BaseService<ManureReg, ManureRegSearch> {
	
	void checkById(String id, int status, String checkUser);
	
	void saveInfo(ManureReg info, MultipartHttpServletRequest fileRequest);
	
	void updateInfo(ManureReg info, MultipartHttpServletRequest fileRequest);
}