package com.xiangxun.atms.common.system.dao;

import com.xiangxun.atms.common.system.vo.SysIndexModelSet;
import com.xiangxun.atms.common.system.vo.SysIndexModelSetSearch;
import com.xiangxun.atms.framework.base.BaseMapper;

public interface SysIndexModelSetMapper extends BaseMapper<SysIndexModelSet, SysIndexModelSetSearch> {
	
	SysIndexModelSet selectByUserId(String userid);
	
}