package com.xiangxun.atms.module.perambulate.mapper;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.perambulate.domain.PerambulateInfo;
import com.xiangxun.atms.module.perambulate.domain.PerambulateInfoSearch;



public interface PerambulateInfoMapper extends BaseMapper<PerambulateInfo, PerambulateInfoSearch> {
	@SuppressWarnings("rawtypes")
	List getCountByDeviceType(Map<String, Object> map);
	List getCountByUser(Map<String, Object> map);
}