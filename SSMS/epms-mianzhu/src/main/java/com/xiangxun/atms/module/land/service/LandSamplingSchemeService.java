package com.xiangxun.atms.module.land.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.module.land.vo.LandSamplingScheme;
import com.xiangxun.atms.module.land.vo.LandSamplingSchemeSearch;

public interface LandSamplingSchemeService extends BaseService<LandSamplingScheme, LandSamplingSchemeSearch> {
	
	void saveInfo(LandSamplingScheme info, MultipartHttpServletRequest fileRequest);
	
	void updateInfo(LandSamplingScheme info, MultipartHttpServletRequest fileRequest);
	
	/**
	 * 获取外业任务可选择的采样方案
	 * @return
	 */
	List<LandSamplingScheme> queryMissionSelectSchemes();
	
	/**
	 * 判断是否可删除
	 * @param id
	 * @return
	 */
	boolean isDelete(String id);
	
}