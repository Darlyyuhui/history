package com.xiangxun.atms.module.sergrade.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

/***
 * 服务商下运维人员的责任资产数量统计
 * @author guikaiping
 */
public interface SergradeCountMapper {
	/***
	 * 服务商下运维人员的责任资产数量统计
	 * @param params map里必须放入userName,factoryName
	 * @return
	 */
	List<Map<String,Object>> selectSergrade(Map<String,Object> params,RowBounds rowBounds);
	
	/***
	 * 服务商下运维人员的责任资产数量统计 柱状图
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> selectChart(Map<String,Object> params);
	
	/***
	 * 用于合计服务
	 * @param params
	 */
	Map<String,Object> selectTotals(Map<String,Object> params);
	
	/***
	 * 获取分页总数
	 * @param params
	 * @return
	 */
	int selectTotalSergrade(Map<String,Object> params);
	
}
