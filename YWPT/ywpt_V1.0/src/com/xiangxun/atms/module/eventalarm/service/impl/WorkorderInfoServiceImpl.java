package com.xiangxun.atms.module.eventalarm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.stereotype.Service;

import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderInfo;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderInfoSearch;
import com.xiangxun.atms.module.eventalarm.mapper.WorkorderInfoMapper;
import com.xiangxun.atms.module.eventalarm.service.WorkorderInfoService;
import com.xiangxun.atms.module.property.domain.CabInfo;
import com.xiangxun.atms.module.property.domain.CabInfoSearch;
import com.xiangxun.atms.module.property.service.AssetInfoService;
import com.xiangxun.atms.module.property.service.CabInfoService;

/**
 * 工单管理业务逻辑接口实现类
 * @author kouyunhao
 *
 */
@Service("workorderInfoService")
public class WorkorderInfoServiceImpl extends AbstractBaseService<WorkorderInfo, WorkorderInfoSearch> implements WorkorderInfoService {

	@Resource
	WorkorderInfoMapper workorderInfoMapper;
	
	@Resource
	CabInfoService cabInfoService;
	
	@Resource
	AssetInfoService assetInfoService;
	
	
	
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
	protected BaseMapper<WorkorderInfo, WorkorderInfoSearch> getBaseMapper() {
		return workorderInfoMapper;
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
	}
	
	@Override
	public boolean xunGengWordOrder(String ip,String deviceType, Date xungengTime) {
		
		//3.根据资产获取到此工单信息
		WorkorderInfoSearch workorderInfoSearch = new WorkorderInfoSearch();
		List<String> values = new ArrayList<String>();
		values.add("0");
		values.add("3");
		workorderInfoSearch.createCriteria().andDeviceipEqualTo(ip).andDevicetypeEqualTo(deviceType).andStatusIn(values);
		List<WorkorderInfo> selectByWorkorder = workorderInfoMapper.selectByExample(workorderInfoSearch);
		if(selectByWorkorder.isEmpty() || selectByWorkorder.size() != 1){
			return false;
		}
		WorkorderInfo workorderInfo = selectByWorkorder.get(0);
		//4.修改此工单巡更状态为1表示已到场，默认0为未到场，增加到场巡更时间
		if(xungengTime == null || ip == null || deviceType == null){
			return false;
		}
		workorderInfo.setXungeng("1");
		workorderInfo.setXungengtime(xungengTime);
		//5.更新数据库
		try {
			workorderInfoMapper.updateByPrimaryKeySelective(workorderInfo);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
