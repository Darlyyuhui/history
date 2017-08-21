package com.xiangxun.atms.common.system.dao;

import java.util.List;

import com.xiangxun.atms.common.system.vo.SystemParams;
import com.xiangxun.atms.common.system.vo.SystemParamsSearch;
import com.xiangxun.atms.framework.base.BaseMapper;

public interface SystemParamsMapper extends BaseMapper<SystemParams, SystemParamsSearch>{
	List<SystemParams> selectByMHName(String name);
}