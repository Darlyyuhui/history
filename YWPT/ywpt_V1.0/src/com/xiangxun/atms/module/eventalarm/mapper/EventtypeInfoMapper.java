package com.xiangxun.atms.module.eventalarm.mapper;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.eventalarm.domain.EventtypeInfo;
import com.xiangxun.atms.module.eventalarm.domain.EventtypeInfoSearch;

public interface EventtypeInfoMapper extends BaseMapper<EventtypeInfo, EventtypeInfoSearch> {
	/***
	 * 故障类型信息
	 * @param params
	 * @return
	 */
	List<EventtypeInfo> selectList(Map<String,Object> params);
	
	/**
	 * 根据code查询告警类型
	 * @param code
	 * @return
	 */
	EventtypeInfo getByCode(String code);
}