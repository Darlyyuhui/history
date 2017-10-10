package com.xiangxun.epms.mobile.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.epms.mobile.business.cache.DicCache;
import com.xiangxun.epms.mobile.business.dao.DicMapper;
import com.xiangxun.epms.mobile.business.domain.Dic;
import com.xiangxun.epms.mobile.business.service.DicService;

@Service
public class DicServiceImpl implements DicService {

	@Resource
	DicMapper dicMapper;
	
	@Override
	public void initDic() {
		List<Map<String, Object>> list = dicMapper.getAllDic();
		
		List<Dic> temp = null;
		Dic d = null;
		String key = null;
		for (Map<String, Object> map : list) {
			key = this.objToString(map.get("TYPE"));
			d = new Dic(this.objToString(map.get("CODE")), this.objToString(map.get("NAME")));
			temp = DicCache.DIC_MAP.get(key);
			if (temp == null) {
				temp = new ArrayList<Dic>();
			}
			temp.add(d);
			DicCache.DIC_MAP.put(key, temp);
		}
	}
	
	private String objToString(Object obj) {
		if (obj == null) {
			return null;
		}
		return obj.toString();
	}

	@Override
	public String getWater(String sampleName) {
		return dicMapper.getWater(sampleName);
	}

	@Override
	public List<Dic> getDicList(String type) {
		return dicMapper.getDicList(type);
	}
     
	@Override
	public List<String> typeList() {
		
		return dicMapper.typeList();
	}
	
	public void simplyType(){
		List<String> list=typeList();
		for(String it:list){
			List<Dic> diclist =getDicList(it);
			DicCache.DIC_SIMPMAP.put(it, diclist);
		}
	}
	
}
