package com.xiangxun.epms.mobile.business.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.epms.mobile.business.domain.SamplingLandReg;
public interface SamplingLandRegService  {
	/**
	 * 获取所有土壤样品信息
	 */
	List<SamplingLandReg> getAllSamplingLandReg();
	/**
	 * 
	 * 增加现场采样信息
	 */
	void insertSelective(SamplingLandReg reg);
	List<String> saveFile( MultipartHttpServletRequest request);
	
	/**
	 * 根据id获取样品信息
	 */
	List<SamplingLandReg> selectByPrimaryKey(String id);
	List<Map<String,Object>> selectByMissionId(String missionId);
	/**
	 * 根据id获取详细信息包括文件列表
	 * @param id
	 * @return
	 */
	List<SamplingLandReg> particular(SamplingLandReg it);
}