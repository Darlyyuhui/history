package com.xiangxun.ywpt.mobile.business.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.ywpt.mobile.business.dao.WorkorderInfoMapper;
import com.xiangxun.ywpt.mobile.business.domain.WorkorderInfo;
import com.xiangxun.ywpt.mobile.business.service.WorkorderStatusService;


/**
 * app端运维人员更改工单状态接口
 * @author miaoxu
 *
 */

@Service("workorderStatusService")
public class WorkorderStatusServiceImpl implements WorkorderStatusService{

	
	@Resource
	WorkorderInfoMapper workorderInfoMapper;
	
	@Override
	public void changeWorkorderStatus(WorkorderInfo workorderInfo) {
		// TODO Auto-generated method stub
		workorderInfoMapper.changeWorkorderStatus(workorderInfo);
		//return 0;
	}

	@Override
	public void upLoadPicture(WorkorderInfo workorderInfo) {
		// TODO Auto-generated method stub
		workorderInfoMapper.upLoadPicture(workorderInfo);
	
	}
}