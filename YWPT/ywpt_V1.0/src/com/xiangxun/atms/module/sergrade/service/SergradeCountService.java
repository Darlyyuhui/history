
package com.xiangxun.atms.module.sergrade.service;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.module.sergrade.domain.SergradeCountReport;

/**
 * 服务商下运维人员的责任资产数量统计服务
 * @author guikaiping
 * @version 1.0
 */
public interface SergradeCountService {
	
	/**
	 * 统计服务商下运维人员的责任资产数量
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @param menuid
	 * @return
	 */
	public Page getSerReport(Map<String, Object> map, int pageNo, int pageSize, String menuid);
	
	/**
	 * 统计服务商下运维人员的责任资产数量 柱状图
	 * @param map
	 * @param startDay
	 * @param endDay
	 * @param menuid
	 * @return voList
	 */
	public List<SergradeCountReport> getSerChartReport(Map<String, Object> map, String menuid);
	
	
	/***
	 * 统计服务商下运维人员的责任资产数量 合计
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @param menuid
	 * @return
	 */
	public SergradeCountReport getSerReportTotal(Map<String, Object> map, String menuid);
	
	
}
