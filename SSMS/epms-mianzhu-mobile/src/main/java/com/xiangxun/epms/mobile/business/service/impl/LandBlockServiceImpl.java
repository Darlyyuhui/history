package com.xiangxun.epms.mobile.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.epms.mobile.business.dao.LandBlockMapper;
import com.xiangxun.epms.mobile.business.domain.LandBlock;
import com.xiangxun.epms.mobile.business.service.LandBlockService;
@Service
public class LandBlockServiceImpl implements LandBlockService {
    @Resource
    LandBlockMapper  landBlockMapper;
	@Override
	public List<LandBlock> findAll() {
		return landBlockMapper.findAll();
	}
	@Override
	public List<LandBlock> findById(String id) {
		return landBlockMapper.findById(id);
	}

}
