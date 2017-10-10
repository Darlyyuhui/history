package com.xiangxun.epms.mobile.business.dao;


import com.xiangxun.epms.mobile.business.domain.AirPoint;
import java.util.List;

public interface AirPointMapper {
	Integer countNUmber();
	List<AirPoint> selectByExample();
	 ;
}