
package com.xiangxun.atms.module.question.service;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.module.question.domain.BreakdownInfo;
import com.xiangxun.atms.module.question.domain.BreakdownInfoSearch;
import com.xiangxun.atms.module.question.domain.BreakdownTypeReport;

/**
 * 故障信息服务
 * @author guikaiping
 * @version 1.0
 */
public interface BreakdownInfoService extends BaseService<BreakdownInfo,BreakdownInfoSearch> {
	
	/***
	 * 按日、周、月、时间段统计频发故障数量
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @param sortType
	 * @param menuid
	 * @return
	 */
	public Page getFreReport(Map<String, Object> map, int pageNo, int pageSize, String sortType, String menuid, String stateType);
	
	/***
	 * 按日、周、月、时间段统计频发故障数量 柱状图
	 * @param map
	 * @param menuid
	 * @return
	 */
	public List<BreakdownTypeReport> getFreChartReport(Map<String, Object> map, String menuid, String startDay, String endDay, String stateType);
	
	/***
	 * 按日、周、月、时间段统计频发故障数量 饼图
	 * @param map
	 * @param menuid
	 * @return
	 */
	public List<BreakdownTypeReport> getFrePieReport(Map<String, Object> map, String menuid, String startDay, String endDay, String stateType);
	
	/***
	 * 按日、周、月、时间段统计频发故障数量 合计使用
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @param sortType
	 * @param menuid
	 * @return
	 */
	public BreakdownTypeReport getFreReportTotal(Map<String, Object> map, String menuid, String stateType);
	
	
}
