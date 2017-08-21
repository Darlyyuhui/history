package com.xiangxun.atms.common.system.dao;

import java.util.List;

import com.xiangxun.atms.common.system.vo.SysIndexModel;
import com.xiangxun.atms.common.system.vo.SysIndexModelSearch;
import com.xiangxun.atms.framework.base.BaseMapper;

public interface SysIndexModelMapper extends BaseMapper<SysIndexModel, SysIndexModelSearch> {
	
	SysIndexModel getByCode(String code);
	
	List<SysIndexModel> selectByIsShow(String isshow);
	
}