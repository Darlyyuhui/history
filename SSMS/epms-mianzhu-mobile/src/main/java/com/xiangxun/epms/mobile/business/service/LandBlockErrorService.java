package com.xiangxun.epms.mobile.business.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.epms.mobile.business.domain.LandBlockError;
import com.xiangxun.epms.mobile.business.util.Page;

public interface LandBlockErrorService {
	Page getListByCondition(LandBlockError it,int pageSize,int  pageNo);
	int countList(LandBlockError it);
	void updateByPrimaryKeySelective(LandBlockError it);
	void insertSelective (LandBlockError it);
	/**
	 * 详情信息
	 */
	LandBlockError selectByPrimaryKey(String id);
	public void saveInfo(LandBlockError info, MultipartHttpServletRequest request);
}
