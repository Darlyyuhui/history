package com.xiangxun.epms.mobile.business.dao;

import java.util.List;

import com.xiangxun.epms.mobile.business.domain.LandBlock;
public interface LandBlockMapper {
	List<LandBlock> findAll();
	List<LandBlock> findById(String id);
}