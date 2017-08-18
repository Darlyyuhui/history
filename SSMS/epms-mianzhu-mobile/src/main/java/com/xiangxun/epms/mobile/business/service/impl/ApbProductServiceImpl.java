package com.xiangxun.epms.mobile.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.epms.mobile.business.dao.ApbProductMapper;
import com.xiangxun.epms.mobile.business.domain.ApbProduct;
import com.xiangxun.epms.mobile.business.service.ApbProuductService;
@Service
public class ApbProductServiceImpl implements ApbProuductService {
    @Resource
    ApbProductMapper apbProductMapper;
	@Override
	public List<ApbProduct> findById(String infoId) {
		return apbProductMapper.findById(infoId);
	}

	@Override
	public ApbProduct findByCode(String codes) {
		return apbProductMapper.findByCode(codes);
	}

}
