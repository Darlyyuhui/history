package com.xiangxun.atms.module.sergrade.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.sergrade.domain.FactoryInfo;
import com.xiangxun.atms.module.sergrade.domain.FactoryInfoSearch;

public interface FactoryInfoMapper extends BaseMapper<FactoryInfo, FactoryInfoSearch>{
	/***
	 * 运维服务商列表信息
	 * @param params
	 * @return
	 */
	List<FactoryInfo> selectList(Map<String,Object> params,RowBounds rowBounds);
	
	/***
	 * 获取分页总数
	 * @param params
	 * @return
	 */
	int selectTotal(Map<String,Object> params);
}