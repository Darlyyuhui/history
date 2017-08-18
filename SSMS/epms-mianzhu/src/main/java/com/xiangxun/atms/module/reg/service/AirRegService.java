package com.xiangxun.atms.module.reg.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.module.reg.vo.AirReg;
import com.xiangxun.atms.module.reg.vo.AirRegSearch;

public interface AirRegService extends BaseService<AirReg, AirRegSearch> {
	
	void checkById(String id, int status, String checkUser);
	
	void saveInfo(AirReg info, MultipartHttpServletRequest fileRequest);
	
	void updateInfo(AirReg info, MultipartHttpServletRequest fileRequest);
	
	/**
	 * 获取采样分析登记可用的项
	 * @return
	 */
	List<AirReg> getInfoByAnalysis();
}