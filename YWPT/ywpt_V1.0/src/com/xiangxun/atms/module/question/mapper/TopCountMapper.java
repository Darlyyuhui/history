package com.xiangxun.atms.module.question.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

/***
 * 频发故障设备TOP统计
 * @author guikaiping
 */
public interface TopCountMapper {
	/***
	 * 频发故障设备TOP统计
	 * @param params map里必须放入startTime,endTime,sortType,days
	 * @return
	 */
	List<Map<String,Object>> selectTop(Map<String,Object> params,RowBounds rowBounds);
	
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
	int selectTotalTop(Map<String,Object> params);
	
}
