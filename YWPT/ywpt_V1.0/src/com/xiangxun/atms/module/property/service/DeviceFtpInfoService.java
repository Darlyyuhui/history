package com.xiangxun.atms.module.property.service;


import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.module.property.domain.DeviceFtpInfo;
import com.xiangxun.atms.module.property.domain.DeviceFtpInfoSearch;

/***
 * FTP相关的 服务接口类
 * 
 * @author yantao
 */
public interface DeviceFtpInfoService extends BaseService<DeviceFtpInfo, DeviceFtpInfoSearch> {

	/***
	 * 获取分页列表数据
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page getFtpByCondition(Map<String, Object> map,int pageNo, int pageSize, String sortType);
	
	public List<DeviceFtpInfo> findAll();
	
	/***
	 * 通过设备编码获取ftp信息
	 * @param deviceCode
	 * @return
	 */
	public DeviceFtpInfo getFtpByDeviceCode(String deviceCode);
	
	/**
	 * 添加ip唯一性验证
	 * @author kouyunhao
	 * @version 1.0
	 * @param ip
	 * @return
	 * Oct 21, 2013
	 */
	public List<DeviceFtpInfo> getByIp(String ip);
}
