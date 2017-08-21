package com.xiangxun.atms.module.property.mapper;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.property.domain.AssetInfo;
import com.xiangxun.atms.module.property.domain.AssetInfoSearch;

public interface AssetInfoMapper extends BaseMapper<AssetInfo, AssetInfoSearch> {
	
	/**
	 * 关联device表查询资产状态，供地图展示用
	 * @return
	 */
	public List<AssetInfo> getAllMapList();
	/**
	 * 查询所有异常的资产信息
	 * @return
	 */
	public List<AssetInfo> getAllMapListByStatusAbnormal();
	
	public void deleteAssetByDeviceId(String deviceId);
	
	public void deleteFactoryContactByDeviceId(String deviceId);
	
	public void deleteDeviceHisByCode(String code);
	
	public void deleteVideoHisByCode(String code);
	
	public void deleteServerHisByCode(String code);
	
	public void deleteModifyByCode(String code);
	
	public void deleteDeviceLogByCode(String code);
	
	public void deleteWorkorderLogByWorkId(String workId);
	
	public void deleteWorkorderByCode(String code);
	
	public void deleteWorkorderAppriseByWorkId(String workId);
	
	public void deleteWorkorderReportById(String id);
	/**
	 * 根据类型(Cabinet)查询所有IP
	 * 
	 */
	public List<Map<String, Object>> selectIpByType();
	
	public List<AssetInfo> countByType();
	public List<AssetInfo> selectAllAsset(AssetInfo assetInfo);
	
	public String getAssetInfoMaxCode();
	public List<AssetInfo> selectAll();
}