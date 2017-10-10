package com.xiangxun.epms.mobile.business.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.epms.mobile.business.dao.NewestCodeMapper;
import com.xiangxun.epms.mobile.business.domain.NewestCode;
import com.xiangxun.epms.mobile.business.service.NewestCodeService;
@Service
public class NewestCodeServiceImpl implements NewestCodeService {
    @Resource
    NewestCodeMapper newestCodeMapper;
	@Override
	public String newwestCode(NewestCode it) {
		
		return newestCodeMapper.newwestCode(it);
	}

}
