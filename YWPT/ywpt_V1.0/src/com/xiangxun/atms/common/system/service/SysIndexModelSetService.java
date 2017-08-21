package com.xiangxun.atms.common.system.service;


import com.xiangxun.atms.common.system.vo.SysIndexModelSet;
import com.xiangxun.atms.common.system.vo.SysIndexModelSetSearch;
import com.xiangxun.atms.framework.base.BaseService;

/***
 * @author yantao
 */
public interface SysIndexModelSetService extends BaseService<SysIndexModelSet, SysIndexModelSetSearch> {

	public SysIndexModelSet selectByUserId(String userid);
	
	
}
