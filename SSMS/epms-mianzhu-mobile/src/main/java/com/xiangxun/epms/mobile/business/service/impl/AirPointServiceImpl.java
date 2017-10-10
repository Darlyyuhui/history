package com.xiangxun.epms.mobile.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.epms.mobile.business.cache.AirPointCache;
import com.xiangxun.epms.mobile.business.dao.AirPointMapper;
import com.xiangxun.epms.mobile.business.domain.AirPoint;
import com.xiangxun.epms.mobile.business.service.AirPointService;
@Service
public class AirPointServiceImpl implements AirPointService {
	@Resource
	AirPointMapper airPointMapper;

	@Override
	public Integer countNUmber() {
		
		return airPointMapper.countNUmber();
	}

	@Override
	public List<AirPoint> selectByExample() {
		return airPointMapper.selectByExample();
	}
	@Override
	public void  getIdName(){
		List<AirPoint> list=airPointMapper.selectByExample();
		List<AirPoint> list1=new ArrayList<>();
		if(list!=null&&list.size()>0){
			for(AirPoint info: list){
				AirPoint airPoint = new AirPoint();
				airPoint.setCode(info.getId());
				airPoint.setName(info.getName());
				list1.add(airPoint);
			}
			
		}
		AirPointCache.AIRPOINT.addAll(list1);
	}

}
