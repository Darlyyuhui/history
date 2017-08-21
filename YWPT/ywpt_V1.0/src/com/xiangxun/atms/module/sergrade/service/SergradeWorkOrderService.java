package com.xiangxun.atms.module.sergrade.service;
import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.module.question.domain.MonthReport;

/***
 * 运维概况统计接口
 * @author guikaiping
 */
public interface SergradeWorkOrderService {
	
	/**
	 * 运维概况统计柱状图
	 * @param map
	 * @param menuid
	 * @return
	 */
	public List<MonthReport> getWorkOrderChartReport(Map<String, Object> map,String menuid);
	
	/***
	 * 自定义时间段
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @param sortType
	 * @param menuid
	 * @return
	 */
	public Page getWorkOrderReport(Map<String, Object> map,int pageNo,int pageSize,String sortType,String menuid);
	
	/***
	 * 自定义时间段合计
	 * @param map
	 * @param menuid
	 * @return
	 */
	public MonthReport getWorkOrderReportTotal(Map<String, Object> map,String menuid);
}
