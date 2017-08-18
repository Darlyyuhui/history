package com.xiangxun.ywpt.mobile.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.xiangxun.ywpt.mobile.business.dao.WorkorderInfoMapper;
import com.xiangxun.ywpt.mobile.business.domain.WorkorderInfo;
import com.xiangxun.ywpt.mobile.business.service.WorkorderInfoService;
import com.xiangxun.ywpt.mobile.business.util.Page;

/**
 * 工单管理业务逻辑接口实现类
 * @author kouyunhao
 *
 */
@Service("workorderInfoService")
public class WorkorderInfoServiceImpl implements WorkorderInfoService {

	@Resource
	WorkorderInfoMapper workorderInfoMapper;
	
	@Override
	public Page queryList(WorkorderInfo workorderInfo, int pageSize, int pageNo) {
		PageHelper.startPage(pageNo, pageSize);
		List<WorkorderInfo> list = workorderInfoMapper.queryList(workorderInfo);
		int totalCount = workorderInfoMapper.countList(workorderInfo);
		
		float s = Float.parseFloat(totalCount+"") / Float.parseFloat(pageSize+"");
		float s1 = Float.parseFloat(pageNo+"");
		if(s + 1 <= s1){
			list = new ArrayList<WorkorderInfo>();
		}
		
		return Page.getPage(totalCount, list, pageNo, pageSize);
		
	}
	
	@Override
	public int searchTotalCount(WorkorderInfo workorderInfo){
		int totalCount = workorderInfoMapper.countList(workorderInfo);
		return totalCount;
	}

	@Override
	public WorkorderInfo watchPicture(WorkorderInfo workorderInfo) {
		// TODO Auto-generated method stub
		WorkorderInfo Workorder = workorderInfoMapper.watchPicture(workorderInfo);
		
		return Workorder;
	}

	@Override
	public List<WorkorderInfo> totalWorkOrder(WorkorderInfo workorderInfo) {
		// TODO Auto-generated method stub
		List<WorkorderInfo> list = workorderInfoMapper.totalWorkOrder(workorderInfo);
		return list;
	}
	
	/*
	@Override
	public List<WorkorderInfo> findAll() {
		WorkorderInfoSearch example = new WorkorderInfoSearch();
		example.createCriteria();
		return workorderInfoMapper.selectByExample(example);
	}

	@Override
	public List<WorkorderInfo> findByType(String type) {
		WorkorderInfoSearch example = new WorkorderInfoSearch();
		example.createCriteria().andDevicetypeEqualTo(type);
		return workorderInfoMapper.selectByExample(example);
	}

	@Override
	public List<WorkorderInfo> findByDeviceCode(String devicecode) {
		WorkorderInfoSearch example = new WorkorderInfoSearch();
		example.createCriteria().andDevicecodeEqualTo(devicecode);
		return workorderInfoMapper.selectByExample(example);
	}

	@Override
	public int countByContact(String contact) {
		return workorderInfoMapper.countByContact(contact);
	}

	@Override
	public List getWorkorderCountByStatus() {
		return workorderInfoMapper.getWorkorderCountByStatus();
	}

	@Override
	public List getWorkorderCountByType() {
		return workorderInfoMapper.getWorkorderCountByType();
	}*/

}
