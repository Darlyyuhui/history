package com.xiangxun.atms.module.property.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import com.xiangxun.atms.icabinet.tools.ICabinetOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.xiangxun.atms.common.deptment.service.DepartmentService;
import com.xiangxun.atms.common.dictionary.service.DicService;
import com.xiangxun.atms.common.road.service.RoadInfoService;
import com.xiangxun.atms.common.user.service.AuthorityFilterContext;
import com.xiangxun.atms.common.user.service.DepartmentAuthorityFilter;
import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.framework.util.ZhongWenToPinYin;
import com.xiangxun.atms.icabinet.tools.ICabinetHelper;
import com.xiangxun.atms.module.property.cache.DeviceCodeFtpCache;
import com.xiangxun.atms.module.property.domain.DeviceInfo;
import com.xiangxun.atms.module.property.domain.DeviceInfoSearch;
import com.xiangxun.atms.module.property.mapper.DeviceCompanyInfoMapper;
import com.xiangxun.atms.module.property.mapper.DeviceInfoMapper;
import com.xiangxun.atms.module.property.service.DeviceFtpInfoService;
import com.xiangxun.atms.module.property.service.DeviceInfoService;

/***
 * 设备管理服务实现类
 * 
 * @author yantao
 */
@Service("deviceInfoService")
public class DeviceInfoServiceImpl extends AbstractBaseService<DeviceInfo, DeviceInfoSearch> implements DeviceInfoService {

	@Resource
	DeviceInfoMapper deviceInfoMapper;

	@Resource
	DeviceCompanyInfoMapper deviceCompanyInfoMapper;

	@Resource
	DicService dicService;

	@Resource
	Cache cache;

	@Resource(name = "authorityFilterContext")
	AuthorityFilterContext authorityFilterContext;

	@Resource
	DepartmentAuthorityFilter departmentAuthorityFilter;

	@Resource
	DepartmentService departmentService;

	@Resource
	RoadInfoService roadInfoService;

	@Resource
	DeviceFtpInfoService ftpService;

	@Resource(name = "deviceCodeFtpCache")
	DeviceCodeFtpCache deviceCodeFtpCache;

	@Autowired
	ICabinetHelper helper;

	@Override
	protected BaseMapper<DeviceInfo, DeviceInfoSearch> getBaseMapper() {
		return deviceInfoMapper;
	}

	@Override
	public Page getDeviceInfoByCondition(Map<String, Object> map, int pageNo, int pageSize, String sortType, String menuid) {
		Map<String, Object> sqlmap = new HashMap<String, Object>();
		if (map != null) {
			sqlmap.putAll(map);
			if (StringUtils.notEmpty(map.get("name") + "")) {
				sqlmap.put("name", "%" + map.get("name").toString() + "%");
			}
			if (StringUtils.notEmpty(map.get("code") + "")) {
				sqlmap.put("code", "%" + map.get("code").toString() + "%");
			}
			if (StringUtils.notEmpty(map.get("ip") + "")) {
				sqlmap.put("ip", "%" + map.get("ip").toString() + "%");
			}
			if (StringUtils.notEmpty(map.get("companyId") + "")) {
				sqlmap.put("companyId", map.get("companyId").toString());
			}
			if (StringUtils.notEmpty(map.get("roadId") + "")) {
				if ("00".equals(map.get("roadId"))) {
					sqlmap.put("roadId", null);
				} else {
					sqlmap.put("roadId", map.get("roadId").toString());
				}
			}
			if (StringUtils.notEmpty(map.get("roadPid") + "")) {
				sqlmap.put("roadPid", map.get("roadPid").toString());
			}
			if (StringUtils.notEmpty(map.get("orgId") + "")) {
				sqlmap.put("orgId", map.get("orgId").toString());
			}
			if (StringUtils.notEmpty(map.get("deviceTypeIds") + "")) {
				List<String> deviceTypeList = getDeviceTypeList(map.get("deviceTypeIds").toString());
				sqlmap.put("deviceTypeIds", deviceTypeList);
			}
			if (StringUtils.notEmpty(map.get("deviceTypeCode") + "")) {
				sqlmap.put("devicetypecode", "%" + map.get("deviceTypeCode").toString() + "%");
			}
		}
		if (StringUtils.notEmpty(sortType)) {
			sqlmap.put("sortColumn", sortType);
		}
		Page page = getListByCondition(sqlmap, pageNo, pageSize, sortType, menuid);		
		return page;
	}

	public List<String> getDeviceTypeList(String deviceTypeStr) {
		String[] deviceTypeArr = deviceTypeStr.split(",");
		List<String> deviceTypeList = new ArrayList<String>();
		for (int i = 0; i < deviceTypeArr.length; i++) {
			deviceTypeList.add(deviceTypeArr[i]);
		}
		return deviceTypeList;
	}

	public Integer getMaxDeviceCodeNum() {
		return deviceInfoMapper.getMaxDeviceCodeNum();
	}

	@SuppressWarnings("unchecked")
	public String getDepartmentIdByDeviceCode(String deviceCode) {
		Table table = (Table) cache.get(DeviceInfo.class.getSimpleName());
		DeviceInfo deviceInfo = (DeviceInfo) table.column(DeviceInfo.class.getSimpleName()).get(deviceCode);
		return deviceInfo.getOrgId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xiangxun.atms.common.device.service.DeviceService#getRoadIdByDeviceCode(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String getRoadIdByDeviceCode(String deviceCode) {
		Table<String, String, DeviceInfo> table = (Table) cache.get(DeviceInfo.class.getSimpleName());
		DeviceInfo deviceInfo = (DeviceInfo) table.column(DeviceInfo.class.getSimpleName()).get(deviceCode);
		if (deviceInfo == null) {
			return deviceInfoMapper.selectRoadIdByDeviceCode(deviceCode);
		}
		return deviceInfo.getRoadId();
	}

	public List<DeviceInfo> getDepartmentIdByDeviceCode() {
		return deviceInfoMapper.selectOrgRoadDevCode();
	}

	@Override
	public List<String> getCodesByDepartmentId(List<String> departmentIds) {
		DeviceInfoSearch search = new DeviceInfoSearch();
		search.createCriteria().andOrgIdIn(departmentIds);
		return deviceInfoMapper.selectCodesByDepartmentId(search);
	}

	// 根据当前登陆用户，查符合权限的所有设备
	@Override
	public List<DeviceInfo> getAllDeviceByAuthority() {
		Map<String, Object> map = new HashMap<String, Object>();
		map = authorityFilterContext.filter(map, null);
		/**
		 * 添加中文首字母排序，解决【卡口监控】-全部设备列表排列混乱问题。
		 */
		map.put("sortType", "nlssort(NAME,'NLS_SORT=SCHINESE_PINYIN_M')");
		return deviceInfoMapper.getListByCondition(map);
	}

	// 根据当前登陆用户，查符合权限的所有设备
	@Override
	public List<DeviceInfo> getAllDeviceByAuthority(String menuid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map = authorityFilterContext.filter(map, menuid);
		/**
		 * 添加中文首字母排序，解决【卡口监控】-全部设备列表排列混乱问题。
		 */
		map.put("sortType", "nlssort(NAME,'NLS_SORT=SCHINESE_PINYIN_M')");
		return deviceInfoMapper.getListByCondition(map);
	}

	// 根据当前登陆用户，查符合权限的所有违法设备
	@Override
	public List<DeviceInfo> getAllDeviceByAuthorityVio() {
		Map<String, Object> map = new HashMap<String, Object>();
		map = authorityFilterContext.filter(map, null);
		return deviceInfoMapper.getListByConditionVio(map);
	}

	// 根据当前登陆用户 和 其他条件 查符合权限的所有设备
	@Override
	public List<DeviceInfo> getAllDeviceByAuthority(Map<String, Object> map) {
		if (map != null) {
			if (StringUtils.notEmpty(map.get("name") + "")) {
				map.put("name", "%" + map.get("name").toString() + "%");
			}
			if (StringUtils.notEmpty(map.get("code") + "")) {
				map.put("code", "%" + map.get("code").toString() + "%");
			}
			if (StringUtils.notEmpty(map.get("companyId") + "")) {
				if ("00".equals(map.get("companyId"))) {
					map.put("companyId", null);
				}
			}
			if (StringUtils.notEmpty(map.get("orgId") + "")) {
				if ("00".equals(map.get("orgId"))) {
					map.put("orgId", null);
				}
			}
			if (StringUtils.notEmpty(map.get("deviceTypeIds") + "")) {
				if ("00".equals(map.get("deviceTypeIds"))) {
					map.put("deviceTypeIds", null);
				}
			}
		}
		map = authorityFilterContext.filter(map, null);
		return deviceInfoMapper.getListByCondition(map);
	}

	// 根据当前登陆用户 和 其他条件 查符合权限的所有设备
	@Override
	public List<DeviceInfo> getAllDeviceByAuthority(Map<String, Object> map, String muneid) {
		if (map != null) {
			if (StringUtils.notEmpty(map.get("name") + "")) {
				map.put("name", "%" + map.get("name").toString() + "%");
			}
			if (StringUtils.notEmpty(map.get("code") + "")) {
				map.put("code", "%" + map.get("code").toString() + "%");
			}
			if (StringUtils.notEmpty(map.get("companyId") + "")) {
				if ("00".equals(map.get("companyId"))) {
					map.put("companyId", null);
				}
			}
			if (StringUtils.notEmpty(map.get("orgId") + "")) {
				if ("00".equals(map.get("orgId"))) {
					map.put("orgId", null);
				}
			}
			if (StringUtils.notEmpty(map.get("deviceTypeIds") + "")) {
				if ("00".equals(map.get("deviceTypeIds"))) {
					map.put("deviceTypeIds", null);
				}
			}
		}
		// map = authorityFilterContext.filterDialog(map, muneid);
		// return deviceInfoMapper.getListByCondition(map);
		return authorityFilterContext.filterDialog(map, muneid);
	}

	// 根据道路ID 获得 设备列表
	public List<DeviceInfo> findDevlistByRoadId(String roadId) {

		
		return deviceInfoMapper.selectDevlistByRoadId(roadId);
	}

	// 根据部门ID 获得 设备列表
	public List<DeviceInfo> findDevlistByOrgId(String orgID) {
		return deviceInfoMapper.selectDevlistByOrgId(orgID);
	}

	private List<DeviceInfo> setDeviceStatus(List<DeviceInfo> list) {
		if (null == list)
			return null;
		for (int i = 0, len = list.size(); i < len; i++) {
			setDeviceStatus(list.get(i));
		}
		return list;
	}

	private DeviceInfo setDeviceStatus(DeviceInfo deviceInfo) {
		if (null == deviceInfo)
			return null;
		if (deviceInfo.getLastdatatime() != null) {
			long timelong = deviceInfo.getLastdatatime().getTime();
			long currentlong = new java.util.Date().getTime();
			BigDecimal timeout = deviceInfo.getTimeout();
			int timeoutper = 60;
			if (timeout != null) {
				if (timeout.intValue() != 0) {
					timeoutper = timeout.intValue();
				}
			}
			if ((currentlong - timelong) > 1000 * 60 * timeoutper) {
				deviceInfo.setDeviceStatFlag(1);
				deviceInfo.setDeviceStatName("故障");
			} else {
				deviceInfo.setDeviceStatFlag(0);
				deviceInfo.setDeviceStatName("正常");
			}
		} else {
			deviceInfo.setDeviceStatFlag(2);
			deviceInfo.setDeviceStatName("未接入");
		}
		return deviceInfo;
	}

	@Override
	public List<String> getDeviceCode(String sql) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("distinct", true);
		hashMap.put("sql", sql);
		return deviceInfoMapper.getDeviceCode(hashMap);
	}

	// 根据设备编号 查设备信息
	@SuppressWarnings("unchecked")
	public DeviceInfo selectDevlistByCode(String code) {
		Table<String, String, DeviceInfo> table = (Table) cache.get(DeviceInfo.class.getSimpleName());
		if (table != null) {
			DeviceInfo deviceInfo = (DeviceInfo) table.column(DeviceInfo.class.getSimpleName()).get(code);
			if (deviceInfo == null) {
				deviceInfo = deviceInfoMapper.selectDevlistByCode(code);
			}
			// 如果deviceInfo还是空，说明原设备信息被删除
			if (deviceInfo != null) {
				String depname = dicService.getNameByKey("Department", null, deviceInfo.getOrgId());
				deviceInfo.setOrgNames(depname);
				String roadname = dicService.getNameByKey("RoadInfo", null, deviceInfo.getRoadId());
				deviceInfo.setRoadName(roadname);
			}
			return deviceInfo;
		}
		return new DeviceInfo();
	}

	// 根据18位设备标号 查设备个数 用于后台2次验证设备编号重复问题
	public Integer countDeviceByCode(String code) {
		return deviceInfoMapper.countDeviceByCode(code);
	}

	// ===================== 基本操作方法 缓存刷新开始 ============================

	@Override
	public List<DeviceInfo> findAll() {
		DeviceInfoSearch search = new DeviceInfoSearch();
		search.createCriteria().andCodeIsNotNull();
		return deviceInfoMapper.selectByExample(search);
	}

	private void refreshCache() {
		// 全部符合权限的设备信息
		List<DeviceInfo> devList = findAll();
		Table<String, String, DeviceInfo> table = HashBasedTable.create();
		for (DeviceInfo info : devList) {
			// 存储的对象为table
			table.put(info.getCode(), DeviceInfo.class.getSimpleName(), info);
		}
		cache.put(DeviceInfo.class.getSimpleName(), table);
		logger.warn("[卡口设备] 缓存初始化完成");

		// ===================== add by kouyunhao 2014-01-21 添加deviceCode-deviceName缓存。
		Table<String, String, String> table1 = HashBasedTable.create();
		for (DeviceInfo device : devList) {
			// 存储的对象为table
			table1.put(device.getCode(), "devcode_name", device.getName());
		}
		cache.put("devcode_name", table1);

		// 全部符合权限的设备信息 按ID存储
		Table<String, String, DeviceInfo> table2 = HashBasedTable.create();
		for (DeviceInfo device : devList) {
			// 存储的对象为table
			table2.put(device.getId(), "DeviceInfoById", device);
		}
		cache.put("DeviceInfoById", table2);

		// 以下缓存 供地点设备对话框用

		// 根据首字母分组合
		Set<DeviceInfo> devListAll = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterA = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterB = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterC = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterD = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterE = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterF = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterG = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterH = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterI = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterJ = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterK = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterL = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterM = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterN = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterO = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterP = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterQ = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterR = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterS = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterT = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterU = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterV = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterW = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterX = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterY = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterZ = new HashSet<DeviceInfo>();

		for (DeviceInfo dev : devList) {
			String devName = dev.getName();
			if (!StringUtils.isEmpty(devName)) {
				String pinyin = ZhongWenToPinYin.getPinYinNormal(devName);
				if (pinyin != null && pinyin.length() > 0) {
					devListAll.add(dev);
					pinyin = pinyin.toLowerCase();
					char word = pinyin.charAt(0);
					if ("a".equals(String.valueOf(word))) {
						devListLetterA.add(dev);
					} else if ("b".equals(String.valueOf(word))) {
						devListLetterB.add(dev);
					} else if ("c".equals(String.valueOf(word))) {
						devListLetterC.add(dev);
					} else if ("d".equals(String.valueOf(word))) {
						devListLetterD.add(dev);
					} else if ("e".equals(String.valueOf(word))) {
						devListLetterE.add(dev);
					} else if ("f".equals(String.valueOf(word))) {
						devListLetterF.add(dev);
					} else if ("g".equals(String.valueOf(word))) {
						devListLetterG.add(dev);
					} else if ("h".equals(String.valueOf(word))) {
						devListLetterH.add(dev);
					} else if ("i".equals(String.valueOf(word))) {
						devListLetterI.add(dev);
					} else if ("j".equals(String.valueOf(word))) {
						devListLetterJ.add(dev);
					} else if ("k".equals(String.valueOf(word))) {
						devListLetterK.add(dev);
					} else if ("l".equals(String.valueOf(word))) {
						devListLetterL.add(dev);
					} else if ("m".equals(String.valueOf(word))) {
						devListLetterM.add(dev);
					} else if ("n".equals(String.valueOf(word))) {
						devListLetterN.add(dev);
					} else if ("o".equals(String.valueOf(word))) {
						devListLetterO.add(dev);
					} else if ("p".equals(String.valueOf(word))) {
						devListLetterP.add(dev);
					} else if ("q".equals(String.valueOf(word))) {
						devListLetterQ.add(dev);
					} else if ("r".equals(String.valueOf(word))) {
						devListLetterR.add(dev);
					} else if ("s".equals(String.valueOf(word))) {
						devListLetterS.add(dev);
					} else if ("t".equals(String.valueOf(word))) {
						devListLetterT.add(dev);
					} else if ("u".equals(String.valueOf(word))) {
						devListLetterU.add(dev);
					} else if ("v".equals(String.valueOf(word))) {
						devListLetterV.add(dev);
					} else if ("w".equals(String.valueOf(word))) {
						devListLetterW.add(dev);
					} else if ("x".equals(String.valueOf(word))) {
						devListLetterX.add(dev);
					} else if ("y".equals(String.valueOf(word))) {
						devListLetterY.add(dev);
					} else if ("z".equals(String.valueOf(word))) {
						devListLetterZ.add(dev);
					}
				}
			}
		}

		Table<String, String, Set<DeviceInfo>> tablelist = HashBasedTable.create();
		tablelist.put("devListAll", "devListLetter", devListAll);
		tablelist.put("devListLetterA", "devListLetter", devListLetterA);
		tablelist.put("devListLetterB", "devListLetter", devListLetterB);
		tablelist.put("devListLetterC", "devListLetter", devListLetterC);
		tablelist.put("devListLetterD", "devListLetter", devListLetterD);
		tablelist.put("devListLetterE", "devListLetter", devListLetterE);
		tablelist.put("devListLetterF", "devListLetter", devListLetterF);
		tablelist.put("devListLetterG", "devListLetter", devListLetterG);
		tablelist.put("devListLetterH", "devListLetter", devListLetterH);
		tablelist.put("devListLetterI", "devListLetter", devListLetterI);
		tablelist.put("devListLetterJ", "devListLetter", devListLetterJ);
		tablelist.put("devListLetterK", "devListLetter", devListLetterK);
		tablelist.put("devListLetterL", "devListLetter", devListLetterL);
		tablelist.put("devListLetterM", "devListLetter", devListLetterM);
		tablelist.put("devListLetterN", "devListLetter", devListLetterN);
		tablelist.put("devListLetterO", "devListLetter", devListLetterO);
		tablelist.put("devListLetterP", "devListLetter", devListLetterP);
		tablelist.put("devListLetterQ", "devListLetter", devListLetterQ);
		tablelist.put("devListLetterR", "devListLetter", devListLetterR);
		tablelist.put("devListLetterS", "devListLetter", devListLetterS);
		tablelist.put("devListLetterT", "devListLetter", devListLetterT);
		tablelist.put("devListLetterU", "devListLetter", devListLetterU);
		tablelist.put("devListLetterV", "devListLetter", devListLetterV);
		tablelist.put("devListLetterW", "devListLetter", devListLetterW);
		tablelist.put("devListLetterX", "devListLetter", devListLetterX);
		tablelist.put("devListLetterY", "devListLetter", devListLetterY);
		tablelist.put("devListLetterZ", "devListLetter", devListLetterZ);
		cache.put("devListLetter", tablelist);

		logger.warn("[卡口设备] 拼音排序缓存初始化完成");

		// 保证设备修改FTP时，同步更新设备与FTP关系缓存
		try {
			deviceCodeFtpCache.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int save(DeviceInfo info) {
		int result = super.save(info);
		if (result > 0) {
			refreshCache();
		}
		return result;
	}

	@Transactional
	public int deleteByExample(DeviceInfoSearch example) {
		int result = super.deleteByExample(example);
		if (result > 0) {
			refreshCache();
		}
		return result;
	}

	@Override
	@Transactional
	public int deleteById(String id) {
		int result = super.deleteById(id);
		if (result > 0) {
			refreshCache();
		}
		return result;
	}

	/**
	 * 添加删除方法，解决[违法系统]-区间管理中从设备中心将已配置到区间规则中的设备删除后，回到区间管理中，区间规则中的设备仍在bug。
	 * 
	 * @author kouyunhao
	 * @version 1.0
	 * @param id
	 * @param deviceInfo
	 * @return int Apr 18, 2014
	 */
	@Override
	@Transactional
	public int deleteById(String id, DeviceInfo deviceInfo) {
		int result = super.deleteById(id, deviceInfo);
		if (result > 0) {
			refreshCache();
		}
		return result;
	}

	@Override
	@Transactional
	public int updateByExample(DeviceInfo record, DeviceInfoSearch example) {
		int result = super.updateByExample(record, example);
		// 刷新缓存
		refreshCache();
		return result;
	}

	@Override
	@Transactional
	public int updateByExampleSelective(DeviceInfo record, DeviceInfoSearch example) {
		int result = super.updateByExampleSelective(record, example);
		// 刷新缓存
		refreshCache();
		return result;
	}

	@Override
	@Transactional
	public int updateById(DeviceInfo record) {
		int result = super.updateById(record);
		// 刷新缓存
		refreshCache();
		return result;
	}

	@Override
	@Transactional
	public int updateByIdSelective(DeviceInfo record) {
		int result = super.updateByIdSelective(record);
		if (result > 0) {
			refreshCache();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DeviceInfo> selectByExampleDiy(Map<String, Object> map, String sortType, String menuid) {
		if (map != null) {
			if (StringUtils.notEmpty(map.get("name") + "")) {
				map.put("name", "%" + map.get("name").toString() + "%");
			}
			if (StringUtils.notEmpty(map.get("code") + "")) {
				map.put("code", "%" + map.get("code").toString() + "%");
			}
			if (StringUtils.notEmpty(map.get("companyId") + "")) {
				map.put("companyId", "%" + map.get("companyId").toString() + "%");
			}
			if (StringUtils.notEmpty(map.get("roadId") + "")) {
				if ("00".equals(map.get("roadId"))) {
					map.put("roadId", null);
				} else {
					map.put("roadId", "%" + map.get("roadId").toString() + "%");
				}
			}
			if (StringUtils.notEmpty(map.get("orgId") + "")) {
				map.put("orgId", "%" + map.get("orgId").toString() + "%");
			}
			if (StringUtils.notEmpty(map.get("deviceTypeIds") + "")) {
				List<String> deviceTypeList = getDeviceTypeList(map.get("deviceTypeIds").toString());
				map.put("deviceTypeIds", deviceTypeList);
			}
			if (StringUtils.notEmpty(map.get("deviceTypeCode") + "")) {
				map.put("devicetypecode", "%" + map.get("deviceTypeCode").toString() + "%");
			}
		}
		if (StringUtils.notEmpty(sortType)) {
			map.put("sortColumn", sortType);
		}
		Page page = getListByCondition(map, 1, 2000, sortType, menuid);
		return page.getResult();
	}

	// ===================== 基本操作方法 缓存刷新结束 ============================

	@Override
	public List<DeviceInfo> findByIp(String ip) {
		DeviceInfoSearch search = new DeviceInfoSearch();
		search.createCriteria().andCodeIsNotNull().andIpEqualTo(ip);
		return deviceInfoMapper.selectByExample(search);
	}

	@Override
	public List<DeviceInfo> selectDevlistByCodeList(String devCodes) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("devCodes", devCodes);
		return deviceInfoMapper.selectDevlistByCodeList(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see@see com.xiangxun.atms.common.device.service.DeviceService#findCurruserDeptDev(java.lang.String)
	 */
	@Override
	public String findCurruserDeptDev(String userid) {
		return deviceInfoMapper.findCurruserDeptDev(userid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xiangxun.atms.common.device.service.DeviceService#getDevListByDeviceTypeCode(java.lang.String)
	 */
	@Override
	public List<DeviceInfo> getDevListByDeviceTypeCode(String deviceTypeCode) {
		return deviceInfoMapper.selectDevListByDeviceTypeCode(deviceTypeCode);
	}

	@Override
	public List<DeviceInfo> getAllVioDevList(String menuid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deviceTypeCode", "vio");// 查询类型为vio下的所有子类
		map = departmentAuthorityFilter.filter(map, menuid);// 添加数据权限
		return setDeviceStatus(deviceInfoMapper.selectDevListByParentDevTypeCode(map));
	}

	// 获得所有设备功能含有 卡口检测 的设备
	public List<DeviceInfo> selectCrossDevList() {
		return deviceInfoMapper.selectCrossDevList();
	}

	@Override
	public void saveHistoryRecord(String id) {
		deviceInfoMapper.insertHistoryRecord(id);
		refreshCache();
	}

	@Override
	public void updatePrimaryKey(String afterId, String beforeId) {
		deviceInfoMapper.updatePrimaryKey(afterId, beforeId);
		refreshCache();
	}

	@Override
	public void updateDeviceType(String sql1, String sql2, String sql3, String sql4) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("sql", sql1);
		deviceInfoMapper.updateDeviceType(hashMap);
		hashMap.put("sql", sql2);
		deviceInfoMapper.updateDeviceType(hashMap);
		hashMap.put("sql", sql3);
		deviceInfoMapper.updateDeviceType(hashMap);
		hashMap.put("sql", sql4);
		deviceInfoMapper.updateDeviceType(hashMap);
		refreshCache();
	}

	/**
	 * 根据厂商ID获取卡口设备信息
	 * 
	 * @param factoryId
	 * @param menuid
	 * @return
	 */
	@Override
	public List<DeviceInfo> getDevListByFactoryid(String factoryId, String menuid) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 厂商ID
		map.put("factoryId", factoryId);
		// 添加数据权限
		map = departmentAuthorityFilter.filter(map, menuid);
		List<DeviceInfo> list = deviceInfoMapper.getListByFactoryid(map);
		return list;
	}

	@Override
	public List<DeviceInfo> findByName(String name) {
		DeviceInfoSearch search = new DeviceInfoSearch();
		search.createCriteria().andNameEqualTo(name);
		return deviceInfoMapper.selectByExample(search);
	}

}
