package com.xiangxun.ywpt.mobile.business.service;

import java.util.List;

import com.xiangxun.ywpt.mobile.business.domain.AssetInfo;
import com.xiangxun.ywpt.mobile.business.domain.DeviceInfo;
import com.xiangxun.ywpt.mobile.business.domain.PerambulateInfo;
import com.xiangxun.ywpt.mobile.business.util.Page;

/**
 * app端设备管理业务逻辑接口
 * @author miaoxu
 *
 */
public interface DeviceInfoService {

	Page queryList(AssetInfo assetInfo, int pageSize, int pageNo);	
	
	
	List<AssetInfo> deviceDetails(AssetInfo assetInfo);


	List<AssetInfo> serverDetails(AssetInfo assetInfo);


	List<AssetInfo> cabinefDetails(AssetInfo assetInfo);


	List<AssetInfo> databaseDetails(AssetInfo assetInfo);


	List<AssetInfo> ftpDetails(AssetInfo assetInfo);


	List<AssetInfo> projectDetails(AssetInfo assetInfo);
	
	//根据条件查卡口数据(经纬度)
	DeviceInfo deviceDetailByContions(PerambulateInfo perambulateInfo);
}
