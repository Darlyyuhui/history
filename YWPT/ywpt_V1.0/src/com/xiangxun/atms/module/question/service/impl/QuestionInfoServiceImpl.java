
package com.xiangxun.atms.module.question.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.common.user.service.AuthorityFilterContext;
import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.module.question.domain.QuestionInfo;
import com.xiangxun.atms.module.question.domain.QuestionInfoSearch;
import com.xiangxun.atms.module.question.mapper.QuestionInfoMapper;
import com.xiangxun.atms.module.question.service.QuestionInfoService;

/**
 * 问题信息服务
 * @author guikaiping
 * @version 1.0
 */
@Service("questionInfoService")
public class QuestionInfoServiceImpl extends AbstractBaseService<QuestionInfo, QuestionInfoSearch> implements QuestionInfoService {

	@Resource
	QuestionInfoMapper questionInfoMapper;
	
	@Resource(name="authorityFilterContext")
	AuthorityFilterContext authorityFilterContext;
	
	@Override
	protected BaseMapper<QuestionInfo, QuestionInfoSearch> getBaseMapper() {
		return questionInfoMapper;
	}
	
	/***
	 * 查询问题信息列表信息服务
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @param sortType
	 * @param menuid
	 * @return
	 */
	@Override
	public Page getReport(Map<String, Object> map, int pageNo, int pageSize, String sortType, String menuid) {
		//加入权限过滤及条件查询
		Map<String, Object> params = filter(map, menuid, null);
		//获取分页数据
		List<QuestionInfo> reports = questionInfoMapper.selectList(params,Page.getRowBounds(pageNo, pageSize));
		//获取记录总数
		int totalCount = questionInfoMapper.selectTotal(params);
		
		return Page.getPage(totalCount, reports, pageNo, pageSize);
	}
	
	/***
	 * 查询问题信息列表信息服务 弹出框
	 * @param map
	 * @param sortType
	 * @param menuid
	 * @return
	 */
	@Override
	public List<QuestionInfo> getReport(Map<String, Object> map, String sortType, String menuid) {
		//加入权限过滤及条件查询
		Map<String, Object> params = filter(map, menuid, null);
		//获取分页数据
		List<QuestionInfo> reports = questionInfoMapper.selectList(params);
		
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
	
}
