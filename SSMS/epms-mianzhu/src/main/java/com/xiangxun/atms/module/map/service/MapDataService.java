package com.xiangxun.atms.module.map.service;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Table;
import com.xiangxun.atms.module.map.vo.AreaCount;
import com.xiangxun.atms.module.map.vo.LandAnalysisCount;
import com.xiangxun.atms.module.map.vo.RepairCount;
import com.xiangxun.atms.module.map.vo.RepairLandBlock;
import com.xiangxun.atms.module.map.vo.RepairProject;
import com.xiangxun.atms.module.reg.vo.AirReg;
import com.xiangxun.atms.module.reg.vo.LandReg;

public interface MapDataService {
	
	/**
	 * 获取土壤统计数据
	 * @param regionId
	 * @param beginTime
	 * @param endTime
	 * @return c=类型名称  row=ph、镉、有效态镉
	 */
	Table<String, String, Float> getDataByMap(String regionId, String beginTime, String endTime);
	
	/**
	 * 获取所有采样登记数
	 * @param regionId
	 * @param beginTime
	 * @param endTime
	 * @return key=登记类型 value=值
	 */
	Map<String, Integer> getRegTypeDataByMap(String regionId, String beginTime, String endTime);
	/**
	 * 获取所有农田土壤采样分析点位
	 * @return
	 */
	List<LandReg> getLandReg(String beginTime, String endTime);
	/**
	 * 获取所有空气采样分析点位
	 * @return
	 */
	List<AirReg> getAirReg(String beginTime, String endTime);
	
	/**
	 * 获取地图首页土壤面积统计
	 * @return
	 */
	AreaCount getMapIndexLandAreaCount();
	
	/**
	 * 获取地图首页的采样统计数据
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	LandAnalysisCount getLandListByIndexMap(String beginTime, String endTime);
	
	/**
	 * 获取修复对比统计
	 * @param proId 修复项目
	 * @param scheduleCode 修复阶段
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	RepairCount getRepairCount(String proId, String scheduleCode, String beginTime, String endTime);
	
	/**
	 * 获取修复项目的地块
	 * @param proId 修复项目
	 * @return
	 */
	List<RepairLandBlock> getRepairLandBlock(String proId);
	
	/**
	 * 获取修复项目
	 * @param size
	 * @return
	 */
	List<RepairProject> getRepairProjects(int size);
}