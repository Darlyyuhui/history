package com.xiangxun.epms.mobile.business.dao;

import java.util.List;

import com.xiangxun.epms.mobile.business.domain.SamplingReg;
public interface SamplingFarmRegMapper {
	List<SamplingReg> findById(String id);
}