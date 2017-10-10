package com.xiangxun.atms.module.statistics.dao;

import java.util.List;
import java.util.Map;

public interface RegMapper {

	List<Map<String, Object>> getList(Map<String, Object> args);
	
}
