package com.xiangxun.atms.module.sergrade.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.sergrade.domain.GradeInfo;
import com.xiangxun.atms.module.sergrade.domain.GradeInfoSearch;

public interface GradeInfoMapper extends BaseMapper<GradeInfo, GradeInfoSearch> {
	/***
	 * 服务级别列表信息
	 * @param params
	 * @return
	 */
	List<GradeInfo> selectList(Map<String,Object> params,RowBounds rowBounds);
	
	/***
	 * 获取分页总数
	 * @param params
	 * @return
	 */
	int selectTotal(Map<String,Object> params);
}