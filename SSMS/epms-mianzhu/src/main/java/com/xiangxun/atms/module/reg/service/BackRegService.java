package com.xiangxun.atms.module.reg.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.module.reg.vo.BackReg;
import com.xiangxun.atms.module.reg.vo.BackRegSearch;

public interface BackRegService extends BaseService<BackReg, BackRegSearch> {
	
	void checkById(String id, int status, String checkUser);
	
	void saveInfo(BackReg info, MultipartHttpServletRequest fileRequest);
	
	void updateInfo(BackReg info, MultipartHttpServletRequest fileRequest);
	
}