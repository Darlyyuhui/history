package com.xiangxun.epms.mobile.business.service;

import java.util.List;
import java.util.Map;

import com.xiangxun.epms.mobile.business.domain.LandSamplingSheme;
import com.xiangxun.epms.mobile.business.util.Page;

public interface LandSamplingShemeService {
	List<LandSamplingSheme> findAll();
	public Page queryList(LandSamplingSheme landSamplingSheme, int pageSize, int pageNo );
	List<LandSamplingSheme> findAllByFinsh(Map<String, Object> args);
	LandSamplingSheme findById(String id);
}
