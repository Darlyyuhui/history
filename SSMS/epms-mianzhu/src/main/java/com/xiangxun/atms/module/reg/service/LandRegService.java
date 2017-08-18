package com.xiangxun.atms.module.reg.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.module.reg.vo.LandReg;
import com.xiangxun.atms.module.reg.vo.LandRegSearch;

public interface LandRegService extends BaseService<LandReg, LandRegSearch> {
	
	void checkById(String id, int status, String checkUser);
	
	void saveInfo(LandReg info, MultipartHttpServletRequest fileRequest);
	
	void updateInfo(LandReg info, MultipartHttpServletRequest fileRequest);
	
	/**
	 * 获取土壤登记的地块信息
	 * @param regId
	 * @return
	 */
	List<String> getLandBlocksByRegId(String regId);
	
	/**
	 * 获取采样分析登记可用的项
	 * @return
	 */
	List<LandReg> getInfoByAnalysis();
}