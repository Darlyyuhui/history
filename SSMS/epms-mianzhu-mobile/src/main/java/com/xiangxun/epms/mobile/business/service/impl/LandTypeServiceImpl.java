package com.xiangxun.epms.mobile.business.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.epms.mobile.business.dao.LandTypeMapper;
import com.xiangxun.epms.mobile.business.domain.LandType;
import com.xiangxun.epms.mobile.business.service.LandTypeService;
@Service
public class LandTypeServiceImpl implements LandTypeService {
    @Resource
    LandTypeMapper landTypeMapper;
	@Override
	public List<Map<String,Object>>  findAll() {
		List<LandType> list =landTypeMapper.findAll();
		List<Map<String,Object>>  resultList = new ArrayList<Map<String,Object>>();
		
		for (LandType lt : list) {
			if(lt.getPid().equals("0")){
				Map<String,Object> map = new HashMap<>();
				map.put("name", lt.getName());
				map.put("code", lt.getCode());
				List<LandType> list1= new ArrayList<>();
				for(LandType info : list){
					if(info.getPid().equals(lt.getId())){
						list1.add(info);
					}
					
				}
				map.put("data", list1);
				resultList.add(map)	;
			}
			
		}
		return resultList;
	}
	@Override
	public LandType findByCode(String code) {
		return landTypeMapper.findByCode(code);
	}

}
