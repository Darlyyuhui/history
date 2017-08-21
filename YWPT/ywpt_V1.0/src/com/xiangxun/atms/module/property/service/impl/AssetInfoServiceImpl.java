package com.xiangxun.atms.module.property.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.common.deptment.service.DepartmentService;
import com.xiangxun.atms.common.deptment.vo.Department;
import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.module.eventalarm.domain.ServerStatus;
import com.xiangxun.atms.module.eventalarm.domain.ServerStatusProcess;
import com.xiangxun.atms.module.eventalarm.domain.ServerStatusProcessSearch;
import com.xiangxun.atms.module.eventalarm.mapper.ServerStatusMapper;
import com.xiangxun.atms.module.eventalarm.mapper.ServerStatusProcessMapper;
import com.xiangxun.atms.module.property.domain.AssetInfo;
import com.xiangxun.atms.module.property.domain.AssetInfoSearch;
import com.xiangxun.atms.module.property.domain.DeviceInfo;
import com.xiangxun.atms.module.property.domain.ServerInfo;
import com.xiangxun.atms.module.property.mapper.AssetInfoMapper;
import com.xiangxun.atms.module.property.service.AssetInfoService;
import com.xiangxun.atms.module.property.service.DeviceInfoService;
import com.xiangxun.atms.module.property.service.ServerInfoService;

/**
 * 资产管理业务逻辑接口实现类
 * @author kouyunhao
 *
 */
@Service("assetInfoService")
public class AssetInfoServiceImpl extends AbstractBaseService<AssetInfo, AssetInfoSearch> implements AssetInfoService {

	@Resource
	AssetInfoMapper assetInfoMapper;
	
	@Resource
	ServerStatusMapper serverStatusMapper;
	
	@Resource
	ServerStatusProcessMapper serverStatusProcessMapper;
	
	@Resource
	DeviceInfoService deviceInfoService;
	
	@Resource
	DepartmentService departmentService;
	
	@Resource
	ServerInfoService serverInfoService;
	
	@Override
	protected BaseMapper<AssetInfo, AssetInfoSearch> getBaseMapper() {
		return assetInfoMapper;
	}
	
	/*@Override
	public List<AssetInfo> findAllAsset(){
		return AssetInfoMapper.selectAllAseet();
		//List<AssetInfo> assetList = AssetInfoMapper.getAllMapList();

	}*/

	
	
	@Override
	public List<AssetInfo> findByDeviceId(String deviceid) {
		AssetInfoSearch example = new AssetInfoSearch();
		example.createCriteria().andDeviceidEqualTo(deviceid);
		return assetInfoMapper.selectByExample(example);
	}

	@Override
	public ServerStatus selectServerStatusByIp(String ip) {
		return serverStatusMapper.selectServerStatusByIp(ip);
	}

	@Override
	public List<ServerStatusProcess> findProcessByIp(String ip) {
		ServerStatusProcessSearch example = new ServerStatusProcessSearch();
		example.createCriteria().andIpEqualTo(ip);
		return serverStatusProcessMapper.selectByExample(example);
	}

	@Override
	public void deleteAssetByDeviceId(String deviceId) {
		assetInfoMapper.deleteAssetByDeviceId(deviceId);
	}

	@Override
	public void deleteFactoryContactByDeviceId(String deviceId) {
		assetInfoMapper.deleteFactoryContactByDeviceId(deviceId);
	}

	@Override
	public void deleteDeviceHisByCode(String code) {
		assetInfoMapper.deleteDeviceHisByCode(code);
	}

	@Override
	public void deleteVideoHisByCode(String code) {
		assetInfoMapper.deleteVideoHisByCode(code);
	}

	@Override
	public void deleteServerHisByCode(String code) {
		assetInfoMapper.deleteServerHisByCode(code);
	}

	@Override
	public void deleteModifyByCode(String code) {
		assetInfoMapper.deleteModifyByCode(code);
	}

	@Override
	public void deleteDeviceLogByCode(String code) {
		assetInfoMapper.deleteDeviceLogByCode(code);
	}

	@Override
	public void deleteWorkorderByCode(String code) {
		assetInfoMapper.deleteWorkorderByCode(code);
	}
	
	@Override
	public void deleteWorkorderReportById(String id) {
		assetInfoMapper.deleteWorkorderReportById(id);
	}

	@Override
	public void deleteWorkorderAppriseByWorkId(String workId) {
		assetInfoMapper.deleteWorkorderAppriseByWorkId(workId);
	}
	
	@Override
	public void deleteWorkorderLogByWorkId(String workId) {
		assetInfoMapper.deleteWorkorderLogByWorkId(workId);
	}

	@Override
	public List<AssetInfo> getAllMapList() {
		return assetInfoMapper.getAllMapList();
	}
	
	@Override
	public List<AssetInfo> getAllMapListByStatusAbnormal() {
		return assetInfoMapper.getAllMapListByStatusAbnormal();
	}
	
	@Override
	public List<Map<String, Object>> selectIp(){
		return assetInfoMapper.selectIpByType();
	}






	@Override
	public List<AssetInfo> findAllAsset(AssetInfo assetInfo) {
		// TODO Auto-generated method stub
		
		return assetInfoMapper.selectAllAsset(assetInfo);
	}


	@Override
	public List<AssetInfo> countByType() {
		return assetInfoMapper.countByType();
	}

	@Override
	public Page getListByCondition(Map<String, Object> map, int pageNo,
			int pageSize, String sortType) {
		Page listByCondition = super.getListByCondition(map, pageNo, pageSize, sortType);
		List<AssetInfo> result = listByCondition.getResult();
		for (AssetInfo assetInfo : result) {
			if("device".equals(assetInfo.getAssettype())){
				DeviceInfo deviceInfo = deviceInfoService.getById(assetInfo.getDeviceid());
				if(deviceInfo != null){
					Department department = departmentService.getById(deviceInfo.getOrgId());
					assetInfo.setOrgName(department.getName());
				}
			}
			if("server".equals(assetInfo.getAssettype())){
				ServerInfo serverInfo = serverInfoService.getById(assetInfo.getDeviceid());
				if(serverInfo != null){
					Department department = departmentService.getById(serverInfo.getOrgId());
					assetInfo.setOrgName(department.getName());	
				}

			}
			
		}
		
		return listByCondition;
	}

	@Override
	public Page getListByCondition(Map<String, Object> map, int pageNo,
			int pageSize, String sortType, String menuid) {
		return super.getListByCondition(map, pageNo, pageSize, sortType, menuid);
	}
	
	@Override
	public String getAssetInfoCode() {
		return assetInfoMapper.getAssetInfoMaxCode();
	}
	public List<AssetInfo> getAll(){
		return assetInfoMapper.selectAll();
	}
	
	
	
}
