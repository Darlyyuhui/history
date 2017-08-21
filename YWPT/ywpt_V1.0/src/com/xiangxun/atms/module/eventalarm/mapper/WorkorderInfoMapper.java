package com.xiangxun.atms.module.eventalarm.mapper;

import java.util.List;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderInfo;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderInfoSearch;

public interface WorkorderInfoMapper extends BaseMapper<WorkorderInfo, WorkorderInfoSearch> {
	
	int countByContact(String contact);

	List getWorkorderCountByStatus();

	List getWorkorderCountByType();
}