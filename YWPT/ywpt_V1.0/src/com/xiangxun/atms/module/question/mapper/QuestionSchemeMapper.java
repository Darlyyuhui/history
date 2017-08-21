package com.xiangxun.atms.module.question.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.question.domain.QuestionScheme;
import com.xiangxun.atms.module.question.domain.QuestionSchemeSearch;

public interface QuestionSchemeMapper extends BaseMapper<QuestionScheme, QuestionSchemeSearch>{
	/***
	 * 方案管理信息列表信息
	 * @param params
	 * @return
	 */
	List<QuestionScheme> selectList(Map<String,Object> params,RowBounds rowBounds);
	
	/***
	 * 获取分页总数
	 * @param params
	 * @return
	 */
	int selectTotal(Map<String,Object> params);
	
	/***
	 * 根据问题信息ID查询方案管理信息列表信息
	 * @param params
	 * @return
	 */
	List<QuestionScheme> selectByInfoId(Map<String,Object> params);
}