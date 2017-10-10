package com.xiangxun.atms.module.map.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.xiangxun.atms.module.map.vo.AreaCount;
import com.xiangxun.atms.module.map.vo.LandAnalysisCount;
import com.xiangxun.atms.module.map.vo.LandCount;
import com.xiangxun.atms.module.map.vo.RepairCount;
import com.xiangxun.atms.module.map.vo.RepairProject;
import com.xiangxun.atms.module.reg.vo.AirReg;
import com.xiangxun.atms.module.reg.vo.LandReg;

public interface MapDataMapper {
	
	/**
	 * 获取土壤统计数据
	 * @param args
	 * @return
	 */
	List<LandCount> getLandCount(Map<String, Object> args);
	
	/**
	 * 获取所有采样登记数
	 * @param args
	 * @return
	 */
	List<Map<String, Object>> getRegTypeCount(Map<String, Object> args);
	
	/**
	 * 获取所有农田土壤采样分析点位
	 * @return
	 */
	List<LandReg> getLandReg(Map<String, Object> args);
	/**
	 * 获取所有空气采样分析点位
	 * @return
	 */
	List<AirReg> getAirReg(Map<String, Object> args);
	
	/**
	 * 获取地图首页土壤面积统计
	 * @return
	 */
	AreaCount getMapIndexLandAreaCount(Map<String, Object> args);
	
	
	/**
	 * 获取地图首页的采样统计数据
	 * @param args
	 * @return
	 */
	LandAnalysisCount getLandListByIndexMap(Map<String, Object> args);
	
	/**
	 * 获取修复对比统计
	 * @param args
	 * @return
	 */
	RepairCount getRepairCount(Map<String, Object> args);
	
	/**
	 * 获取修复项目的地块
	 * @param args
	 * @return
	 */
	List<Map<String, Object>> getRepairLandBlock(Map<String, Object> args);
	
	/**
	 * 获取修复项目
	 * @param args
	 * @return
	 */
	List<RepairProject> getRepairProjects(Map<String, Object> args, RowBounds rowBounds);
}