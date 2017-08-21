package com.xiangxun.atms.module.eventalarm.service;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.module.eventalarm.domain.AlarmLog;
import com.xiangxun.atms.module.eventalarm.domain.AlarmLogSearch;

/**
 * 告警日志管理业务逻辑接口
 * @author kouyunhao
 *
 */
public interface AlarmLogService extends BaseService<AlarmLog, AlarmLogSearch> {
	
	String[] findEventAttributes(String eventType);
	
	public List getAlarmtypeCount();
	
	public List getAlarmgradeCount();
	
	public List<AlarmLog> getNewAlarm(String sql);
	
	public List<AlarmLog> getNewAlarmAll(String sql);
	
	public List getListByConditionOne();
}
