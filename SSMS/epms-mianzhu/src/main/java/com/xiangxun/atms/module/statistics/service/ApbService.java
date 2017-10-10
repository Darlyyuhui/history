package com.xiangxun.atms.module.statistics.service;

import java.util.Map;

import com.xiangxun.atms.framework.base.Page;

public interface ApbService {

	Page queryPage(int pageNo, int pageSize, String sortType, Map<String, Object> args);
	
}
