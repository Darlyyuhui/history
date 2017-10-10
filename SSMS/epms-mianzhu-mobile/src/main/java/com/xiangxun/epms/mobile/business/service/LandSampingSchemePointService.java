package com.xiangxun.epms.mobile.business.service;

import java.util.List;
import java.util.Map;

import com.xiangxun.epms.mobile.business.domain.LandSamplingSchemePoint;

public interface LandSampingSchemePointService {
	/**
	 * 根据采样方案id获取对应的点位
	 * 
	 */
	List<LandSamplingSchemePoint> getLandSamplingSchemePointByPlanId(Map<String,Object> args);
    /**
     * 修改土壤采样方案点位
     *
     */
	void updateLandSamplingSchemePointById(LandSamplingSchemePoint landSamplingSchemePoint);
	/**
	 * 新增采样方案点位
	 */
	void insertSelective(LandSamplingSchemePoint landSamplingSchemePoint);
	/**
	 * 根据id获取变动的点位
	 */
	List<Map<String,Object>> getLandSamplingSchemePointById(String id);
	/**
	 * 根据方案id获取数量
	 */
	int contlist(String id);
	/**
	 * 根据方案Id获取已经采样的点位信息
	 */
	List<LandSamplingSchemePoint> findByPlanIdSamplin(String schemeId);
	/**
	 * 根据code值查询code是否存在
	 */
	LandSamplingSchemePoint  findByCode(String code);
	/**
	 * 获取最新code值
	 */
	String  getNewestCode(String value);
}
