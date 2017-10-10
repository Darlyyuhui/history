package com.xiangxun.atms.module.statistics.dao;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.module.statistics.vo.RepairInfo;

public interface RepairMapper {

	List<RepairInfo> getList(Map<String, Object> args);
	
}
