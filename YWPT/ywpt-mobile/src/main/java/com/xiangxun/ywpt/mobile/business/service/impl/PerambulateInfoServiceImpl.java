package com.xiangxun.ywpt.mobile.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.xiangxun.ywpt.mobile.business.dao.PerambulateInfoMapper;
import com.xiangxun.ywpt.mobile.business.domain.PerambulateInfo;
import com.xiangxun.ywpt.mobile.business.service.PerambulateInfoService;
import com.xiangxun.ywpt.mobile.business.util.Page;

@Service("perambulateInfoService")
public class PerambulateInfoServiceImpl implements PerambulateInfoService{

	@Resource
	PerambulateInfoMapper perambulateInfoMapper;
	
	@Override
	public void perambulateUp(PerambulateInfo perambulateInfo) {

		perambulateInfoMapper.perambulateUp(perambulateInfo);		
	}
	
	@Override
	public Page queryList(PerambulateInfo perambulateInfo, int pageSize,int pageNo) {
		
		PageHelper.startPage(pageNo, pageSize);

		List<PerambulateInfo> list = perambulateInfoMapper.queryList(perambulateInfo);
		int totalCount = perambulateInfoMapper.countList(perambulateInfo);
		
		float s = Float.parseFloat(totalCount+"") / Float.parseFloat(pageSize+"");
		float s1 = Float.parseFloat(pageNo+"");
		if(s + 1 <= s1){
			list = new ArrayList<PerambulateInfo>();
		}
		
		return Page.getPage(totalCount, list, pageNo, pageSize);
	}

}
