package com.xiangxun.atms.module.bs.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.module.bs.vo.StandardIndustry;
import com.xiangxun.atms.module.bs.vo.StandardIndustrySearch;

public interface StandardIndustryService extends BaseService<StandardIndustry, StandardIndustrySearch> {

	/**
	 * 带附件添加
	 */
	void saveInfo(StandardIndustry info, MultipartHttpServletRequest fileRequest);
	/**
	 * 带附件修改
	 */
	void updateInfo(StandardIndustry info, MultipartHttpServletRequest fileRequest);
}