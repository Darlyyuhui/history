package com.xiangxun.epms.mobile.business.service;

import java.util.List;

import com.xiangxun.epms.mobile.business.domain.AirPoint;

public interface RegTypeService {
	//根据任务id获取任务下所有现场采样已经选择的大气点位的id,删除已选择的点位
	List<AirPoint> findPointIdByMissionId(String missionId);
}
