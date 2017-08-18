package com.xiangxun.epms.mobile.business.dao;

import java.util.List;

import com.xiangxun.epms.mobile.business.domain.LandType;
public interface LandTypeMapper {
     List<LandType>	findAll();
     LandType findByCode(String code);
}