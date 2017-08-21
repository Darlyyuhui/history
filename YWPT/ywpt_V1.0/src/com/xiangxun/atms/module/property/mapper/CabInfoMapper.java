package com.xiangxun.atms.module.property.mapper;

import java.util.List;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.property.domain.AssetInfo;
import com.xiangxun.atms.module.property.domain.CabInfo;
import com.xiangxun.atms.module.property.domain.CabInfoSearch;


public interface CabInfoMapper extends BaseMapper<CabInfo, CabInfoSearch>  {
	public List<CabInfo> getAll();
}