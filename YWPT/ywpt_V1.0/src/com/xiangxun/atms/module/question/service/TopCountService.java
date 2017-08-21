
package com.xiangxun.atms.module.question.service;

import java.util.Map;

import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.module.question.domain.BreakdownTypeReport;

/**
 * 频发故障设备TOP统计信息服务
 * @author guikaiping
 * @version 1.0
 */
public interface TopCountService {
	
	/***
	 * 按日、周、月、时间段频发故障设备TOP统计
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @param sortType
	 * @param menuid
	 * @return
	 */
	public Page getReport(Map<String, Object> map, int pageNo, int pageSize, String sortType, String menuid, String stateType);
	
	/***
	 * 按日、周、月、时间段频发故障设备TOP统计 合计使用
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @param sortType
	 * @param menuid
	 * @return
	 */
	public BreakdownTypeReport getReportTotal(Map<String, Object> map, String menuid, String stateType);
	
	
}
