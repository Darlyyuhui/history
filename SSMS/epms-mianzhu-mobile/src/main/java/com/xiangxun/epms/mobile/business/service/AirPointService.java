package com.xiangxun.epms.mobile.business.service;

import java.util.List;

import com.xiangxun.epms.mobile.business.domain.AirPoint;

public interface AirPointService {
	Integer countNUmber();
	List<AirPoint> selectByExample();
	void  getIdName();
}
