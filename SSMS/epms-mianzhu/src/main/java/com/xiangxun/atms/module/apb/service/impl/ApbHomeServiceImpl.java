package com.xiangxun.atms.module.apb.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.module.apb.dao.ApbHomeMapper;
import com.xiangxun.atms.module.apb.service.ApbHomeService;

@Service
public class ApbHomeServiceImpl implements ApbHomeService {

	@Resource
	ApbHomeMapper apbHomeMapper;

	@Override
	public Map<String, Object> getTopCount() {
		List<Map<String, Object>> list = apbHomeMapper.getTopCount();
		Map<String, Object> map = new HashMap<String, Object>();
		for (Map<String, Object> m : list) {
			map.put(m.get("NAME_").toString(), m.get("NUM_"));
		}
		return map;
	}

	@Override
	public List<Map<String, Object>> getApbInfoAreaCount() {
		return apbHomeMapper.getApbInfoAreaCount();
	}

	@Override
	public List<Map<String, Object>> getApbProductList(int pageSize) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageSize", pageSize);
		return apbHomeMapper.getApbProductList(args);
	}

	@Override
	public List<Map<String, Object>> getProductTypeCount() {
		return apbHomeMapper.getProductTypeCount();
	}
	
}
