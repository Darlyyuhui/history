package com.xiangxun.atms.module.statistics.dao;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.module.statistics.vo.AnalysisInfo;

public interface AnalysisMapper {

	List<AnalysisInfo> getLandList(Map<String, Object> args);
	
}
