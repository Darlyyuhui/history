package com.xiangxun.atms.module.property.service;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.module.eventalarm.domain.ServerStatus;
import com.xiangxun.atms.module.eventalarm.domain.ServerStatusProcess;
import com.xiangxun.atms.module.property.domain.AssetInfo;
import com.xiangxun.atms.module.property.domain.AssetInfoSearch;
import com.xiangxun.atms.module.property.mapper.AssetInfoMapper;

/**
 * 资产管理业务逻辑接口
 * @author kouyunhao
 *
 */
public interface AssetInfoService extends BaseService<AssetInfo, AssetInfoSearch> {
	
	List<AssetInfo> findByDeviceId(String deviceid);
	
	ServerStatus selectServerStatusByIp(String ip);
	
	List<ServerStatusProcess> findProcessByIp(String ip);
	
	List<Map<String, Object>> selectIp();
	
	public List<AssetInfo> findAllAsset(AssetInfo assetInfo);
	
	/**
	 * 关联device表查询资产状态，供地图展示用
	 * @return
	 */
	public List<AssetInfo> getAllMapList();
	
	/**
	 * 查询所有的异常信息
	 * @return
	 */
	public List<AssetInfo> getAllMapListByStatusAbnormal();
	
	//同步删除方法集合
	public void deleteAssetByDeviceId(String deviceId);
	
	public void deleteFactoryContactByDeviceId(String deviceId);
	
	public void deleteDeviceHisByCode(String code);
	
	public void deleteVideoHisByCode(String code);
	
	public void deleteServerHisByCode(String code);
	
	public void deleteModifyByCode(String code);
	
	//同步删除工单信息
	public void deleteDeviceLogByCode(String code);
	
	public void deleteWorkorderLogByWorkId(String workId);
	
	public void deleteWorkorderByCode(String code);
	
	public void deleteWorkorderAppriseByWorkId(String workId);
	
	public void deleteWorkorderReportById(String id);
	
	public List<AssetInfo> countByType();
	
	public String getAssetInfoCode();
	public List<AssetInfo> getAll();

}
