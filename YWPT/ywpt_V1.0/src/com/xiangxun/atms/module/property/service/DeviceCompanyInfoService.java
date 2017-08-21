package com.xiangxun.atms.module.property.service;



import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.module.property.domain.DeviceCompanyInfo;
import com.xiangxun.atms.module.property.domain.DeviceCompanyInfoSearch;





/***
 * 设备建设厂家信息 服务接口类
 * @author yantao
 */
public interface DeviceCompanyInfoService extends BaseService<DeviceCompanyInfo,DeviceCompanyInfoSearch>{
	

	/***
	 * 获取分页列表数据
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page getCompanyInfoByCondition(Map<String, Object> searchParams,int pageNo,int pageSize,String sortType);
	
	/***
	 * 获取所有的设备提供公司
	 * @return
	 */
	public List<DeviceCompanyInfo> findAll();
	
	
}
