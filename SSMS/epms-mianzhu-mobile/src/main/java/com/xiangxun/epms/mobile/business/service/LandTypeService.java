package com.xiangxun.epms.mobile.business.service;

import java.util.List;
import java.util.Map;

import com.xiangxun.epms.mobile.business.domain.LandType;

public interface LandTypeService {
	/**
	 * 查询所有土壤
	 */
	List<Map<String,Object>> 	findAll();
	 /**
	  * 根据code查询信息
	  */
	 LandType findByCode(String code);
}
