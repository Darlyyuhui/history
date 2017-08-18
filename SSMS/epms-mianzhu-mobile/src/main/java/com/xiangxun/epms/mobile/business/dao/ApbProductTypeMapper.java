package com.xiangxun.epms.mobile.business.dao;

import com.xiangxun.epms.mobile.business.domain.ApbProductType;

public interface ApbProductTypeMapper {
	ApbProductType selectByPrimaryKey(String code);
}