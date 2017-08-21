package com.xiangxun.atms.module.eventalarm.mapper;

import java.util.HashMap;
import java.util.List;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.eventalarm.domain.AlarmLog;
import com.xiangxun.atms.module.eventalarm.domain.AlarmLogSearch;

public interface AlarmLogMapper extends BaseMapper<AlarmLog, AlarmLogSearch> {
	
	List selectAlarmtimesByType();
	
	List selectAlarmtimesByGrade();
	
	List<AlarmLog> getNewAlarm(HashMap<String, Object> hashMap);
	
	List<AlarmLog> getNewAlarmAll(HashMap<String, Object> hashMap);
	
	void deleteStatusByJob();
	
	List getListByConditionOne();
}