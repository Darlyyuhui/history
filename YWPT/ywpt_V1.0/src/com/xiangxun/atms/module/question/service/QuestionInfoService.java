
package com.xiangxun.atms.module.question.service;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.module.question.domain.QuestionInfo;
import com.xiangxun.atms.module.question.domain.QuestionInfoSearch;

/**
 * 问题信息服务
 * @author guikaiping
 * @version 1.0
 */
public interface QuestionInfoService extends BaseService<QuestionInfo,QuestionInfoSearch> {
	/***
	 * 查询问题信息列表信息服务
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @param sortType
	 * @param menuid
	 * @return
	 */
	public Page getReport(Map<String, Object> map, int pageNo, int pageSize, String sortType, String menuid);

	/***
	 * 查询问题信息列表信息服务 弹出框
	 * @param map
	 * @param sortType
	 * @param menuid
	 * @return
	 */
	public List<QuestionInfo> getReport(Map<String, Object> map, String sortType, String menuid);
}
