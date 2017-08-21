
package com.xiangxun.atms.module.sergrade.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.common.user.service.AuthorityFilterContext;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.module.sergrade.domain.SergradeCountReport;
import com.xiangxun.atms.module.sergrade.mapper.SergradeCountMapper;
import com.xiangxun.atms.module.sergrade.service.SergradeCountService;

/**
 * 服务商下运维人员的责任资产数量统计服务
 * @author guikaiping
 * @version 1.0
 */
@Service("sergradeCountService")
public class SergradeCountServiceImpl implements SergradeCountService {
	
	@Resource
	SergradeCountMapper sergradeCountMapper;
	
	@Resource(name="authorityFilterContext")
	AuthorityFilterContext authorityFilterContext;
	
	/**
	 * 统计服务商下运维人员的责任资产数量
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @param menuid
	 * @return
	 */
	@Override
	public Page getSerReport(Map<String, Object> map, int pageNo, int pageSize, String menuid) {
		return getSergrade(map, pageNo, pageSize, menuid);
	}
	
	/***
	 * 统计服务商下运维人员的责任资产数量 合计
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @param menuid
	 * @return
	 */
	@Override
	public SergradeCountReport getSerReportTotal(Map<String, Object> map, String menuid) {
		SergradeCountReport sergradeCountReport = getSerTotal(map, menuid);
		return sergradeCountReport;
	}

	/**
	 * 统计统计服务商下运维人员的责任资产数量 柱状图
	 * @param map
	 * @param startDay
	 * @param endDay
	 * @param menuid
	 * @return voList
	 */
	public List<SergradeCountReport> getSerChartReport(Map<String, Object> map, String menuid) {
		return getSerChart(map, menuid);
	}

	
	/***
	 * 统计统计服务商下运维人员的责任资产数量 合计数据
	 * @param map
	 * @param menuid
	 * @return SergradeCountReport
	 */
	private SergradeCountReport getSerTotal(Map<String, Object> map, String menuid) {
		//加入权限过滤及条件查询
		Map<String, Object> params = filter(map, menuid);
		Map<String, Object> maps  = sergradeCountMapper.selectTotals(params);
		BigDecimal[] values = new BigDecimal[6];

		if(null != maps && !maps.isEmpty()){
			for (int i = 0; i < 6; i++) {
				values[i] = (BigDecimal) maps.get("A"+(i+1));	
			}
		}
		SergradeCountReport sergradeCountReport = new SergradeCountReport();
		sergradeCountReport.setValues(values);
		sergradeCountReport.setTotals(sergradeCountReport.getResultTotals());
		
		return sergradeCountReport;
	}

	/***
	 * 统计统计服务商下运维人员的责任资产数量
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @param menuid
	 * @return
	 */
	private Page getSergrade(Map<String, Object> map, int pageNo, int pageSize, String menuid) {
		//加入权限过滤及条件查询
		Map<String, Object> params = filter(map, menuid);
		
		List<Map<String,Object>> lists = sergradeCountMapper.selectSergrade(params, Page.getRowBounds(pageNo,pageSize));
		List<SergradeCountReport> results = new ArrayList<SergradeCountReport>();
		SergradeCountReport sergradeCountReport = null;
		for (Map<String, Object> maps : lists) {
			sergradeCountReport = new SergradeCountReport();
			if(null != maps && !maps.isEmpty()){
				//服务商名称
				String factoryName = "";
				if (null != maps.get("FACTORYNAME")) {
					factoryName = maps.get("FACTORYNAME").toString();
				}
				sergradeCountReport.setFactoryName(factoryName);
				
				BigDecimal[] values = new BigDecimal[6];
				
				for (int i = 0; i < 6; i++) {
					values[i] = (BigDecimal) maps.get("A"+(i+1));
				}
				sergradeCountReport.setValues(values);

				sergradeCountReport.setTotals(sergradeCountReport.getResultTotals());
				results.add(sergradeCountReport);
			}
		}
		//获取记录总数
		int totalCount = sergradeCountMapper.selectTotalSergrade(params);
		
		return Page.getPage(totalCount, results, pageNo, pageSize);
	}
	/***
	 * 统计统计服务商下运维人员的责任资产数量 柱状图 
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @param menuid
	 * @return
	 */
	private List<SergradeCountReport> getSerChart(Map<String, Object> map, String menuid) {
		List<SergradeCountReport> voList = new ArrayList<SergradeCountReport>();
		if (voList.size() >= 0) {
			voList.clear();
		}
		//加入权限过滤及条件查询
		Map<String, Object> params = filter(map, menuid);
		List<Map<String,Object>> lists = sergradeCountMapper.selectChart(params);
		SergradeCountReport sergradeCountReport = null;
		for (int i = 0; i < lists.size(); i++) {
			Map<String, Object> maps = lists.get(i);
			if(null != maps && !maps.isEmpty()){
				sergradeCountReport = new SergradeCountReport();
				//服务商名称
				String factoryName = "";
				if (null != maps.get("FACTORYNAME")) {
					factoryName = maps.get("FACTORYNAME").toString();
				}
				sergradeCountReport.setFactoryName(factoryName);
				//卡口运维设备数量
				sergradeCountReport.setDeviceCounts((BigDecimal) maps.get("A1"));
				//服务器运维设备数量
				sergradeCountReport.setServerCounts((BigDecimal) maps.get("A2"));
				//数据库运维设备数量
				sergradeCountReport.setDatabaseCounts((BigDecimal) maps.get("A3"));
				//FTP运维设备数量
				sergradeCountReport.setFtpCounts((BigDecimal) maps.get("A4"));
				//平台运维设备数量
				sergradeCountReport.setProjectCounts((BigDecimal) maps.get("A5"));
				//机柜运维设备数量
				sergradeCountReport.setCabinetCounts((BigDecimal) maps.get("A6"));
			}
			if (null != sergradeCountReport) {
				voList.add(sergradeCountReport);
			}
		}
		
		return voList;
	}
	
	/**
	 * 处理查询参数
	 * @param map
	 * @param menuid
	 * @param stateType
	 * @return
	 */
	private Map<String, Object> filter(Map<String, Object> map, String menuid) {
		//加入权限过滤
		Map<String, Object> params = authorityFilterContext.filter(map, menuid);
				
		return params;
	}
	
}
