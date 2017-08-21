package com.xiangxun.atms.module.property.service;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.module.property.domain.DatabaseInfo;
import com.xiangxun.atms.module.property.domain.FtpInfo;
import com.xiangxun.atms.module.property.domain.FtpInfoSearch;

/**
 * FTP信息业务逻辑接口
 * @author kouyunhao
 *
 */
public interface FtpInfoService extends BaseService<FtpInfo, FtpInfoSearch> {
	
	List<FtpInfo> findAll();
	
	List<FtpInfo> findByIp(String ip);
	
	List<FtpInfo> findByName(String name);
	
	/**
	 * 根据厂商ID获取FTP信息
	 * @param factoryId
	 * @param menuid
	 * @return
	 */
	public List<FtpInfo> getFtpListByFactoryid(String factoryId);
	
	/***
	 * 导出EXCEL专用
	 * @return
	 */
	public List<FtpInfo> selectByExampleDiy(Map<String, Object> searchParams,String sortType,String menuid);
	

}
