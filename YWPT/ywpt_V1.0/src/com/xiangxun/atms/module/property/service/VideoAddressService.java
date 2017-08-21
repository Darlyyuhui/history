package com.xiangxun.atms.module.property.service;


import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.module.property.domain.VideoAddressInfo;
import com.xiangxun.atms.module.property.domain.VideoAddressInfoSearch;





/***
 * 监控设备相关的 服务接口类
 * @author yantao
 */
public interface VideoAddressService extends BaseService<VideoAddressInfo,VideoAddressInfoSearch>{
	
	/***
	 * 获取分页列表数据
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page getVideoAddressByCondition(Map<String, Object> map,int pageNo,int pageSize,String sortType);
	
	
	/***
	 * 获取所有数据
	 * @return
	 */
	public List<VideoAddressInfo> findIsRealiTimeAll(String isRealiTime);
	
}
