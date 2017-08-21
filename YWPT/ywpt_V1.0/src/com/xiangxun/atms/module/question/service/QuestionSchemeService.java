
package com.xiangxun.atms.module.question.service;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.module.question.domain.QuestionScheme;
import com.xiangxun.atms.module.question.domain.QuestionSchemeSearch;

/**
 * 方案管理信息服务
 * @author guikaiping
 * @version 1.0
 */
public interface QuestionSchemeService extends BaseService<QuestionScheme,QuestionSchemeSearch> {
	/***
	 * 查询方案管理信息列表信息服务
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @param sortType
	 * @param menuid
	 * @return
	 */
	public Page getReport(Map<String, Object> map, int pageNo, int pageSize, String sortType, String menuid);

	/***
	 * 根据问题信息ID查询方案管理信息列表信息服务
	 * @param map
	 * @param menuid
	 * @return
	 */
	public List<QuestionScheme> getReportByInfoId(Map<String, Object> map, String menuid);
}
