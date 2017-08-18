package com.xiangxun.epms.mobile.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.epms.mobile.business.dao.LandTypeMapper;
import com.xiangxun.epms.mobile.business.domain.LandType;
import com.xiangxun.epms.mobile.business.service.LandTypeService;
@Service
public class LandTypeServiceImpl implements LandTypeService {
    @Resource
    LandTypeMapper landTypeMapper;
	@Override
	public List<LandType> findAll() {
		return landTypeMapper.findAll();
	}
	@Override
	public LandType findByCode(String code) {
		return landTypeMapper.findByCode(code);
	}

}
