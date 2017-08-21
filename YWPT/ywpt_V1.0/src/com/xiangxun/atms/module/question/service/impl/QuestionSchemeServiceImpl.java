
package com.xiangxun.atms.module.question.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.common.user.service.AuthorityFilterContext;
import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.module.question.domain.QuestionScheme;
import com.xiangxun.atms.module.question.domain.QuestionSchemeSearch;
import com.xiangxun.atms.module.question.mapper.QuestionSchemeMapper;
import com.xiangxun.atms.module.question.service.QuestionSchemeService;

/**
 * 方案管理信息服务
 * @author guikaiping
 * @version 1.0
 */
@Service("questionSchemeService")
public class QuestionSchemeServiceImpl extends AbstractBaseService<QuestionScheme, QuestionSchemeSearch> implements QuestionSchemeService {

	@Resource
	QuestionSchemeMapper questionSchemeMapper;
	
	@Resource(name="authorityFilterContext")
	AuthorityFilterContext authorityFilterContext;
	
	@Override
	protected BaseMapper<QuestionScheme, QuestionSchemeSearch> getBaseMapper() {
		return questionSchemeMapper;
	}
	
	/***
	 * 查询方案管理信息列表信息服务
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
		params.put("sortType", sortType);
		//获取分页数据
		List<QuestionScheme> reports = questionSchemeMapper.selectList(params,Page.getRowBounds(pageNo, pageSize));
		//获取记录总数
		int totalCount = questionSchemeMapper.selectTotal(params);
		
		return Page.getPage(totalCount, reports, pageNo, pageSize);
	}
	
	/***
	 * 根据问题信息ID查询方案管理信息列表信息服务
	 * @param map
	 * @param menuid
	 * @return
	 */
	@Override
	public List<QuestionScheme> getReportByInfoId(Map<String, Object> map, String menuid) {
		//加入权限过滤及条件查询
		Map<String, Object> params = filter(map, menuid, null);
		//获取分页数据
		List<QuestionScheme> reports = questionSchemeMapper.selectByInfoId(params);
		
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
