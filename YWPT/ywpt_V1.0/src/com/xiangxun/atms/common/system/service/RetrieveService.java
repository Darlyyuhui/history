package com.xiangxun.atms.common.system.service;


import java.util.List;
import java.util.Map;

import com.xiangxun.atms.common.system.vo.RetrieveInfo;
import com.xiangxun.atms.common.system.vo.RetrieveInfoSearch;
import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.framework.base.Page;

/***
 * 系统回收站相关 服务接口类
 * 
 * @author yantao
 */
public interface RetrieveService extends BaseService<RetrieveInfo, RetrieveInfoSearch> {

	
	public Page getByCondition(Map<String, Object> map,int pageNo, int pageSize, String sortType);
	
	public List<RetrieveInfo> findAll();
	
}
