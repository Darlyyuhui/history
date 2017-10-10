package com.xiangxun.atms.module.statistics.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.module.statistics.dao.ApbMapper;
import com.xiangxun.atms.module.statistics.service.ApbService;
import com.xiangxun.atms.module.statistics.vo.ApbInfo;

@Service
public class ApbServiceImpl implements ApbService {

	@Resource
	ApbMapper apbMapper;
	
	@Override
	public Page queryPage(int pageNo, int pageSize, String sortType, Map<String, Object> args) {
		args.put("sortType", sortType);
		List<ApbInfo> list = apbMapper.queryList(args, Page.getRowBounds(pageNo, pageSize));
		int totalCount = apbMapper.countList(args);
		return Page.getPage(totalCount, list, pageNo, pageSize);
	}

}
