
package com.xiangxun.atms.module.gplot.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.module.gplot.domain.StatusApp;
import com.xiangxun.atms.module.gplot.domain.StatusAppSearch;

/**
 * 设备拓扑图服务
 * @author miaoxu
 * @version 1.0
 */
public interface GplotSearchService extends BaseService <StatusApp,StatusAppSearch>{

	/***
	 * 获取根据字段TYPE分类的数据
	 * @param 
	 * @return
	 */
	public List<StatusApp> getTypeByCondition(Map<String,String> searchParams);


	
}
