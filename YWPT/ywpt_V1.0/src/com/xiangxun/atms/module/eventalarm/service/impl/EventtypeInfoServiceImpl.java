package com.xiangxun.atms.module.eventalarm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.common.user.service.AuthorityFilterContext;
import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.eventalarm.domain.EventtypeInfo;
import com.xiangxun.atms.module.eventalarm.domain.EventtypeInfoSearch;
import com.xiangxun.atms.module.eventalarm.mapper.EventtypeInfoMapper;
import com.xiangxun.atms.module.eventalarm.service.EventtypeInfoService;

/**
 * 事件类型业务逻辑接口实现类
 * @author KOUYUNHAO
 *
 */
@Service("eventtypeInfoService")
public class EventtypeInfoServiceImpl extends AbstractBaseService<EventtypeInfo, EventtypeInfoSearch> implements EventtypeInfoService {
	
	@Resource
	EventtypeInfoMapper eventtypeInfoMapper;
	
	@Resource(name="authorityFilterContext")
	AuthorityFilterContext authorityFilterContext;

	@Override
	protected BaseMapper<EventtypeInfo, EventtypeInfoSearch> getBaseMapper() {
		return eventtypeInfoMapper;
	}

	@Override
	public List<EventtypeInfo> findByName(String name) {
		EventtypeInfoSearch example = new EventtypeInfoSearch();
		example.createCriteria().andNameEqualTo(name);
		return eventtypeInfoMapper.selectByExample(example);
	}

	@Override
	public List<EventtypeInfo> findAll() {
		EventtypeInfoSearch example = new EventtypeInfoSearch();
		example.createCriteria();
		return eventtypeInfoMapper.selectByExample(example);
	}
	
	/***
	 * 查询故障类型信息列表信息服务
	 * @param map
	 * @param menuid
	 * @return
	 */
	@Override
	public List<EventtypeInfo> getReport(Map<String, Object> map, String menuid) {
		//加入权限过滤及条件查询
		Map<String, Object> params = filter(map, menuid, null);
		//获取分页数据
		List<EventtypeInfo> reports = eventtypeInfoMapper.selectList(params);
		
		return reports;
	}
	
	/**
	 * 处理查询参数
	 * @param map
	 * @param menuid
	 * @param stateType
	 * @return
	 */
	private Map<String, Object> filter(Map<String, Object> map, String menuid, String stateType) {
		//加入权限过滤
		Map<String, Object> params = authorityFilterContext.filter(map, menuid);
		return params;
	}

	@Override
	public List<EventtypeInfo> findByCode(String code) {
		EventtypeInfoSearch example = new EventtypeInfoSearch();
		example.createCriteria().andCodeEqualTo(code);
		return eventtypeInfoMapper.selectByExample(example);
	}
	
	/**
	 * 根据code查询告警类型
	 * @param code
	 * @return
	 */
	@Override
	public EventtypeInfo getByCode(String code) {
		EventtypeInfo eventtypeInfo = eventtypeInfoMapper.getByCode(code);
		return eventtypeInfo;
	}

}
