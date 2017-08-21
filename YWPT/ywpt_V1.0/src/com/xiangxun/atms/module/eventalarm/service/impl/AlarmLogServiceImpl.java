package com.xiangxun.atms.module.eventalarm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.xiangxun.atms.common.dictionary.service.DicService;
import com.xiangxun.atms.common.dictionary.type.DicType;
import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.module.eventalarm.domain.AlarmLog;
import com.xiangxun.atms.module.eventalarm.domain.AlarmLogSearch;
import com.xiangxun.atms.module.eventalarm.domain.EventlevelInfo;
import com.xiangxun.atms.module.eventalarm.domain.EventtypeInfo;
import com.xiangxun.atms.module.eventalarm.mapper.AlarmLogMapper;
import com.xiangxun.atms.module.eventalarm.service.AlarmLogService;
import com.xiangxun.atms.module.eventalarm.service.EventlevelInfoService;
import com.xiangxun.atms.module.eventalarm.service.EventtypeInfoService;

/**
 * 告警日志管理业务逻辑接口实现类
 * @author kouyunhao
 *
 */
@Service("alarmLogService")
public class AlarmLogServiceImpl extends AbstractBaseService<AlarmLog, AlarmLogSearch> implements AlarmLogService {

	@Resource
	AlarmLogMapper alarmLogMapper;
	
	@Resource
	EventtypeInfoService eventtypeInfoService;
	
	@Resource
	EventlevelInfoService eventlevelInfoService;
	
	@Resource
	DicService dicService;
	
	@Override
	public String[] findEventAttributes(String eventType) {
		String[] attribute = new String[4];
		List<EventtypeInfo> eventtypeList = eventtypeInfoService.findByCode(eventType);
		if(eventtypeList != null && eventtypeList.size() != 0){
			EventtypeInfo eventtype = eventtypeList.get(0);
			attribute[0] = eventtype.getName();
			String levelId = eventtype.getRelationLevel();
			EventlevelInfo eventlevel = eventlevelInfoService.getById(levelId);
			if(eventlevel != null){
				attribute[1] = eventlevel.getName();
				attribute[2] = eventlevel.getRelationType();
				String type = eventlevel.getRelationType();
				if(type != null){
					String[] types = type.split(",");
					StringBuilder sb = new StringBuilder();
					for(String alarmtype : types){
						sb.append(dicService.getDicByCodeAndType(alarmtype, DicType.ALARMTYPE).getName());
						sb.append(",");
					}
					attribute[2] = StringUtils.removeEnd(sb.toString(), ",");
				}
				attribute[3] = eventlevel.getColor();
			}
		}
		return attribute;
	}

	@Override
	protected BaseMapper<AlarmLog, AlarmLogSearch> getBaseMapper() {
		return alarmLogMapper;
	}

	@Override
	public List getAlarmtypeCount() {
		return alarmLogMapper.selectAlarmtimesByType();
	}
	
	@Override
	public List getAlarmgradeCount() {
		return alarmLogMapper.selectAlarmtimesByGrade();
	}
	
	@Override
	public List<AlarmLog> getNewAlarm(String sql) {
		AlarmLogSearch search = new AlarmLogSearch();
		search.createCriteria().andIdIsNotNull();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("distinct", true);
		hashMap.put("oredCriteria",search.getOredCriteria());
		hashMap.put("sql", sql);
		return alarmLogMapper.getNewAlarm(hashMap);
	}
	
	@Override
	public List<AlarmLog> getNewAlarmAll(String sql) {
		AlarmLogSearch search = new AlarmLogSearch();
		search.createCriteria().andIdIsNotNull();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("distinct", true);
		hashMap.put("oredCriteria",search.getOredCriteria());
		hashMap.put("sql", sql);
		return alarmLogMapper.getNewAlarmAll(hashMap);
	}

	@Override
	public List getListByConditionOne() {
		List<HashMap> listByConditionOne = alarmLogMapper.getListByConditionOne();
		List<AlarmLog> list = new ArrayList<AlarmLog>();
		
		for (HashMap hashMap : listByConditionOne) {
			AlarmLog alarmLog = new AlarmLog();
			alarmLog.setDeviceCode(hashMap.get("CODE").toString());
			alarmLog.setDeviceName(hashMap.get("NAME").toString());
			alarmLog.setDeviceIp(hashMap.get("IP").toString());
			alarmLog.setDeviceType(hashMap.get("DEVICETYPE").toString());
			alarmLog.setAlarmTime((Date)hashMap.get("TIME"));
//			alarmLog.setAlarmTime(DateUtil.stringFormatToDate((String)hashMap.get("TIME"), "yyyy-mm-dd hh24:mi:ss"));
			alarmLog.setEventType(hashMap.get("EVENTTYPE").toString());
			alarmLog.setCount(hashMap.get("COUNT").toString());
			list.add(alarmLog);
		}
		
		return list;
	}
	

}
