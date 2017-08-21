package com.xiangxun.atms.module.eventalarm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.eventalarm.domain.EventlevelInfo;
import com.xiangxun.atms.module.eventalarm.domain.EventlevelInfoSearch;
import com.xiangxun.atms.module.eventalarm.mapper.EventlevelInfoMapper;
import com.xiangxun.atms.module.eventalarm.service.EventlevelInfoService;

/**
 * 事件级别业务逻辑接口实现类
 * @author KOUYUNHAO
 *
 */
@Service("eventlevelInfoService")
public class EventlevelInfoServiceImpl extends AbstractBaseService<EventlevelInfo, EventlevelInfoSearch> implements EventlevelInfoService {
	
	@Resource
	EventlevelInfoMapper eventlevelInfoMapper;

	@Override
	protected BaseMapper<EventlevelInfo, EventlevelInfoSearch> getBaseMapper() {
		return eventlevelInfoMapper;
	}
	
	@Override
	public List<EventlevelInfo> findAll() {
		EventlevelInfoSearch example = new EventlevelInfoSearch();
		example.createCriteria().andIdIsNotNull();
		return eventlevelInfoMapper.selectByExample(example);
	}

	@Override
	public List<EventlevelInfo> findByName(String name) {
		EventlevelInfoSearch example = new EventlevelInfoSearch();
		example.createCriteria().andNameEqualTo(name);
		return eventlevelInfoMapper.selectByExample(example);
	}

}
