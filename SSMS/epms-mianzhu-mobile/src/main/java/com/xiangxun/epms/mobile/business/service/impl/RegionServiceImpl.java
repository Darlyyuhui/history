package com.xiangxun.epms.mobile.business.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xiangxun.epms.mobile.business.cache.RegionCache;
import com.xiangxun.epms.mobile.business.dao.RegionMapper;
import com.xiangxun.epms.mobile.business.domain.Region;
import com.xiangxun.epms.mobile.business.service.RegionService;
@Service
public class RegionServiceImpl implements RegionService {
    @Resource
    RegionMapper regionMapper;
	@Override
	public List<Region> getAllRegion() {
		return regionMapper.getAllRegion();
	}
	@Override
	public List<Region> getAllRegionById(String id) {
		return regionMapper.getAllRegionById(id);
	}
	@Override
	public List<Region> getAllRegionByName(String name) {
		
		return regionMapper.getAllRegionByName(name);
	}
	@Override
	public void initRegion() {
		List<Region> list=this.getAllRegion();
		for(Region info:list){
			if(!"0".equals(info.getId())){
			  RegionCache.NAME_ID.put(info.getName(), info.getId());
			}
		}
	}
	@Override
	public String getFullNameById(String id) {
		if (StringUtils.isEmpty(id)) {
			return null;
		}
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("id", id);
		List<Map<String,Object>> list = regionMapper.getFullNameById(args);
		if (list == null || list.size() == 0) {
			return null;
		}
		StringBuilder str = new StringBuilder();
		for (Map<String,Object> m : list) {
			str.append(m.get("NAME"));
		}
		return str.toString();
	}

}
