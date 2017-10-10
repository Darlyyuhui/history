package com.xiangxun.epms.mobile.business.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xiangxun.epms.mobile.business.dao.LandSamplingSchemePointMapper;
import com.xiangxun.epms.mobile.business.domain.LandSamplingSchemePoint;
import com.xiangxun.epms.mobile.business.domain.NewestCode;
import com.xiangxun.epms.mobile.business.service.LandSampingSchemePointService;
import com.xiangxun.epms.mobile.business.service.NewestCodeService;
import com.xiangxun.epms.mobile.business.util.CodeUtil;

@Service
public class LandSampingSchemePointServiceImpl implements  LandSampingSchemePointService{
	@Resource
    LandSamplingSchemePointMapper landSampingSchemePointMapper;
	@Resource
	NewestCodeService newestCodeservice;
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	private final String  FRONT="TR";
	private final Integer DIGIT=4;
	private final String TABLENAME="T_LAND_SAMPLING_SCHEME_POINT";
	private final String CONTTION="SCHEME_ID";
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

	@Override
	public LandSamplingSchemePoint findByCode(String code) {
		return landSampingSchemePointMapper.findByCode(code);
	}

	@Override
	public String getNewestCode(String value) {
		String code="";
		String resulltCode=null;
		try{
		
		if(StringUtils.isEmpty(TABLENAME)){
			logger.info("deficiency tableName");
			return null;
		}
		 NewestCode newestCode= new NewestCode(TABLENAME,CONTTION,value);
		 code=newestCodeservice.newwestCode(newestCode);
		 resulltCode= CodeUtil.setCode(code, DIGIT);
		 resulltCode=FRONT+resulltCode;
		}catch(Exception e){
			logger.error("create"+TABLENAME+" newestCode error:"+e.getMessage());
		}
		return resulltCode;
	
	}

}
