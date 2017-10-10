package com.xiangxun.atms.module.statistics.service;

import java.util.List;

import com.xiangxun.atms.module.statistics.vo.AnalysisInfo;

public interface AnalysisService {

	List<AnalysisInfo> getLandList(String regionId, String beginTime, String endTime);
	
}
