package com.xiangxun.atms.module.eventalarm.service;

import java.util.List;

import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.module.eventalarm.domain.EventlevelInfo;
import com.xiangxun.atms.module.eventalarm.domain.EventlevelInfoSearch;

/**
 * 事件级别业务逻辑接口
 * @author KOUYUNHAO
 *
 */
public interface EventlevelInfoService extends BaseService<EventlevelInfo, EventlevelInfoSearch> {
	
	List<EventlevelInfo> findAll();
	
	List<EventlevelInfo> findByName(String name);
	
}
