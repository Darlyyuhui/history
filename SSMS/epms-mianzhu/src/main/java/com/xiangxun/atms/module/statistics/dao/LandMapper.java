package com.xiangxun.atms.module.statistics.dao;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.module.statistics.vo.LandLine;
import com.xiangxun.atms.module.statistics.vo.LandPie;

public interface LandMapper {

	/**
	 * 获取饼图用统计数据
	 * @param args
	 * @return
	 */
	List<LandPie> getPieData(Map<String, Object> args);
	List<LandPie> getPieDataAll(Map<String, Object> args);
	
	/**
	 * 获取柱状用统计数据
	 * @param args
	 * @return
	 */
	List<LandLine> getLineData(Map<String, Object> args);
	
}
