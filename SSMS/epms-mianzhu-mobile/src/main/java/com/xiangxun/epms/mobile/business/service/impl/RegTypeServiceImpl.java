package com.xiangxun.epms.mobile.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.epms.mobile.business.cache.AirPointCache;
import com.xiangxun.epms.mobile.business.dao.SamplingAirRegMapper;
import com.xiangxun.epms.mobile.business.domain.AirPoint;
import com.xiangxun.epms.mobile.business.service.RegTypeService;
@Service
public class RegTypeServiceImpl implements RegTypeService {
    @Resource
    SamplingAirRegMapper samplingAirRegMapper;
	@Override
	public List<AirPoint> findPointIdByMissionId(String missionId) {
		List<AirPoint> airPointListt=AirPointCache.AIRPOINT;
		List<AirPoint> list= new ArrayList<AirPoint>();
		list.addAll(airPointListt);
		List<String>  pointIdList=samplingAirRegMapper.findPointIdByMissionId(missionId);
		for(String it:pointIdList){
			for(int i=0;i<list.size();i++){
				AirPoint info=list.get(i);
				if(it!=null&&it.equals(info.getCode())){
					list.remove(i);
					
				}
			}
		}
		return list;
	}

}
