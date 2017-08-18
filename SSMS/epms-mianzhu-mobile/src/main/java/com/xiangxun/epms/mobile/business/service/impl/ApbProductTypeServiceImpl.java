package com.xiangxun.epms.mobile.business.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.epms.mobile.business.dao.ApbProductTypeMapper;
import com.xiangxun.epms.mobile.business.domain.ApbProductType;
import com.xiangxun.epms.mobile.business.service.ApbProductTypeService;

@Service
public class ApbProductTypeServiceImpl  implements ApbProductTypeService {
    @Resource
    private ApbProductTypeMapper apbProductTypeMapper;

	@Override
	public ApbProductType selectByPrimaryKey(String code) {
		
		return apbProductTypeMapper.selectByPrimaryKey(code);
	}

}