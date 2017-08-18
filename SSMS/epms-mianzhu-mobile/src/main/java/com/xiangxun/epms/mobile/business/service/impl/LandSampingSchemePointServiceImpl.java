package com.xiangxun.epms.mobile.business.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiangxun.epms.mobile.business.dao.LandSamplingSchemePointMapper;
import com.xiangxun.epms.mobile.business.domain.LandSamplingSchemePoint;
import com.xiangxun.epms.mobile.business.service.LandSampingSchemePointService;

@Service
public class LandSampingSchemePointServiceImpl implements  LandSampingSchemePointService{
	@Resource
    LandSamplingSchemePointMapper landSampingSchemePointMapper;
	@Override
	public List<LandSamplingSchemePoint> getLandSamplingSchemePointByPlanId(Map<String,Object> args) {
		return landSampingSchemePointMapper.getLandSamplingSchemePointByPlanId(args);
	}

	@Override
	public void updateLandSamplingSchemePointById(LandSamplingSchemePoint landSamplingSchemePoint) {
		 landSampingSchemePointMapper.updateLandSamplingSchemePointById(landSamplingSchemePoint);
	}

	@Override
	public void insertSelective(LandSamplingSchemePoint landSamplingSchemePoint) {
		landSampingSchemePointMapper.insertSelective(landSamplingSchemePoint);
		
	}

	@Override
	public List<Map<String,Object>> getLandSamplingSchemePointById(String id) {
		List<LandSamplingSchemePoint> list =landSampingSchemePointMapper. getLandSamplingSchemePointById( id);
		 List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		 if(list!=null&&list.size() >0){
		   for(LandSamplingSchemePoint info :list){
			 Map<String,Object> map = new HashMap<String,Object>();
			 map.put("unique", info.getId());
	   		 map.put("data", info);
	     	 resultList.add(map);
		   }
		 }
		return resultList;
	}

	@Override
	public int contlist(String id) {
		
		return landSampingSchemePointMapper.contlist(id);
	}

	@Override
	public List<LandSamplingSchemePoint> findByPlanIdSamplin(String schemeId) {
		List<LandSamplingSchemePoint> list=landSampingSchemePointMapper.findByPlanIdSamplin(schemeId);
		for(LandSamplingSchemePoint info:list){
			if(info.getCode()==null){
				info.setCode("");
			}
			if(info.getLatitude()==null){
				info.setLatitude(new BigDecimal(0));
			}
			if(info.getLongitude()==null){
				info.setLongitude(new BigDecimal(0));
			}
		}
		return list;
	}

}
