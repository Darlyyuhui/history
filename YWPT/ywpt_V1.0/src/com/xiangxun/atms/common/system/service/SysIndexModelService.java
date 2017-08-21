package com.xiangxun.atms.common.system.service;


import java.util.List;
import java.util.Map;

import com.xiangxun.atms.common.system.vo.SysIndexModel;
import com.xiangxun.atms.common.system.vo.SysIndexModelSearch;
import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.framework.base.Page;

/***
 * @author yantao
 */
public interface SysIndexModelService extends BaseService<SysIndexModel, SysIndexModelSearch> {

	
	public Page getByCondition(Map<String, Object> map,int pageNo, int pageSize, String sortType);
	
	public List<SysIndexModel> findAll();
	
	public SysIndexModel getByCode(String code);
	
	public List<SysIndexModel> selectByIsShow(String isshow);
	
}
