package com.xiangxun.atms.module.statistics.service;

import java.util.List;

import com.xiangxun.atms.module.statistics.vo.LandACd;
import com.xiangxun.atms.module.statistics.vo.LandCd;
import com.xiangxun.atms.module.statistics.vo.LandLine;
import com.xiangxun.atms.module.statistics.vo.LandPh;
import com.xiangxun.atms.module.statistics.vo.LandPie;

public interface LandService {

	/**
	 * 获取饼图统计数据
	 * @param regionId
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	List<LandPie> getPieData(String regionId, String beginTime, String endTime);
	List<LandPie> getPieDataAll(String beginTime, String endTime);
	/**
	 * 获取柱状图统计数据
	 * @param regionId
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	List<LandLine> getLineData(String regionId, String beginTime, String endTime);
	
	/**
	 * 根据类型获取监测配置
	 * @param dicTypeCode
	 * @return String[2]{min~max, level}
	 */
	List<String[]> getStandMByDicTypeCode(String dicTypeCode);
	
	LandPh getPhData(String regionId, String beginTime, String endTime);
	LandCd getCdData(String regionId, String beginTime, String endTime);
	LandACd getACdData(String regionId, String beginTime, String endTime);
	
}
