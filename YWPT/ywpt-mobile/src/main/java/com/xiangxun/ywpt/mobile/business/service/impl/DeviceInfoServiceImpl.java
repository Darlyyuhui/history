package com.xiangxun.ywpt.mobile.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.xiangxun.ywpt.mobile.business.dao.AssetInfoMapper;
import com.xiangxun.ywpt.mobile.business.dao.DeviceInfoMapper;
import com.xiangxun.ywpt.mobile.business.domain.AssetInfo;
import com.xiangxun.ywpt.mobile.business.domain.DeviceInfo;
import com.xiangxun.ywpt.mobile.business.domain.PerambulateInfo;
import com.xiangxun.ywpt.mobile.business.service.DeviceInfoService;
import com.xiangxun.ywpt.mobile.business.util.Page;

/**
 * app端设备管理业务逻辑接口
 * @author miaoxu
 *
 */
@Service("DeviceInfoService")
public class DeviceInfoServiceImpl implements DeviceInfoService {
	
	@Resource
	AssetInfoMapper assetInfoMapper;

	@Resource
	DeviceInfoMapper deviceInfoMapper;
	/**
	 * 分页查询资产表里面的设备
	 * @author miaoxu
	 *
	 */
	@Override
	public Page queryList(AssetInfo assetInfo, int pageSize, int pageNo) {
		PageHelper.startPage(pageNo, pageSize);

		List<AssetInfo> list = assetInfoMapper.queryList(assetInfo);
		int totalCount = assetInfoMapper.countList(assetInfo);
		
		float s = Float.parseFloat(totalCount+"") / Float.parseFloat(pageSize+"");
		float s1 = Float.parseFloat(pageNo+"");
		if(s + 1 <= s1){
			list = new ArrayList<AssetInfo>();
		}
		
		return Page.getPage(totalCount, list, pageNo, pageSize);
	}

	/**
	 * 查询某一个卡口设备的详情
	 * @author miaoxu
	 *
	 */
	@Override
	public List<AssetInfo> deviceDetails(AssetInfo assetInfo) {
		List<AssetInfo> assetList = assetInfoMapper.deviceDetails(assetInfo);
		return assetList;
		/*DeviceInfo device = deviceInfoMapper.deviceDetails(deviceInfo);
		return device;*/
	}

	@Override
	public List<AssetInfo> serverDetails(AssetInfo assetInfo) {
		List<AssetInfo> assetList = assetInfoMapper.serverDetails(assetInfo);
		return assetList;
	}

	@Override
	public List<AssetInfo> cabinefDetails(AssetInfo assetInfo) {
		List<AssetInfo> assetList = assetInfoMapper.cabinefDetails(assetInfo);
		return assetList;
	}

	@Override
	public List<AssetInfo> databaseDetails(AssetInfo assetInfo) {
		List<AssetInfo> assetList = assetInfoMapper.databaseDetails(assetInfo);
		return assetList;
	}

	@Override
	public List<AssetInfo> ftpDetails(AssetInfo assetInfo) {
		List<AssetInfo> assetList = assetInfoMapper.ftpDetails(assetInfo);
		return assetList;
	}

	@Override
	public List<AssetInfo> projectDetails(AssetInfo assetInfo) {
		List<AssetInfo> assetList = assetInfoMapper.projectDetails(assetInfo);
		return assetList;
	}

	@Override
	public DeviceInfo deviceDetailByContions(PerambulateInfo perambulateInfo) {
		DeviceInfo deviceInfo = deviceInfoMapper.deviceDetailByContions(perambulateInfo);
		return deviceInfo;
	}


	
	
}
