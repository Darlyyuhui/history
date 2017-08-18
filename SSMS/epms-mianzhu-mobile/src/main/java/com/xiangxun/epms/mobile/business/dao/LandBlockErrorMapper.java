package com.xiangxun.epms.mobile.business.dao;

import java.util.List;

import com.xiangxun.epms.mobile.business.domain.LandBlockError;

public interface LandBlockErrorMapper {
	List<LandBlockError> getListByCondition(LandBlockError it);
	int countList(LandBlockError it);
	void updateByPrimaryKeySelective(LandBlockError it);
	void insertSelective (LandBlockError it);
	LandBlockError selectByPrimaryKey(String id);
}