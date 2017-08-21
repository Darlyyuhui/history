package com.xiangxun.atms.module.eventalarm.service;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.module.eventalarm.domain.EventtypeInfo;
import com.xiangxun.atms.module.eventalarm.domain.EventtypeInfoSearch;

/**
 * 事件类型业务逻辑接口
 * @author KOUYUNHAO
 *
 */
public interface EventtypeInfoService extends BaseService<EventtypeInfo, EventtypeInfoSearch> {
	
	List<EventtypeInfo> findAll();
	
	List<EventtypeInfo> findByName(String name);
	
	List<EventtypeInfo> findByCode(String code);
	
	/***
	 * 查询故障类型信息列表信息服务
	 * @param map
	 * @param menuid
	 * @return
	 */
	public List<EventtypeInfo> getReport(Map<String, Object> map, String menuid);
	
	/**
	 * 根据code查询告警类型
	 * @param code
	 * @return
	 */
	public EventtypeInfo getByCode(String code);
}
