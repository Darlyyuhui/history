package com.xiangxun.atms.module.sergrade.mapper;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.sergrade.domain.FactoryContact;
import com.xiangxun.atms.module.sergrade.domain.FactoryContactSearch;

public interface FactoryContactMapper extends BaseMapper<FactoryContact, FactoryContactSearch> {
	/***
	 * 获取已分配的责任设备列表
	 * @param params
	 * @return
	 */
	List<FactoryContact> selectList(Map<String,Object> params);
}