package com.xiangxun.epms.mobile.business.service;

import java.util.List;

import com.xiangxun.epms.mobile.business.domain.LandBlock;

public interface LandBlockService {
	/**
	 * 查询地块所有信息
	 */
	List<LandBlock> findAll();
	/**
	 * 根据id查询地块所有信息
	 */
	List<LandBlock> findById(String id);
}
