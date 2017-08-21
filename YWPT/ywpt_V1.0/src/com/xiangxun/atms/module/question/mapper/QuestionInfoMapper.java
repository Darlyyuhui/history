package com.xiangxun.atms.module.question.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.question.domain.QuestionInfo;
import com.xiangxun.atms.module.question.domain.QuestionInfoSearch;

public interface QuestionInfoMapper extends BaseMapper<QuestionInfo, QuestionInfoSearch>{
	/***
	 * 问题信息列表信息
	 * @param params
	 * @return
	 */
	List<QuestionInfo> selectList(Map<String,Object> params,RowBounds rowBounds);
	
	/***
	 * 问题信息列表信息 弹出框
	 * @param params
	 * @return
	 */
	List<QuestionInfo> selectList(Map<String,Object> params);
	
	/***
	 * 获取分页总数
	 * @param params
	 * @return
	 */
	int selectTotal(Map<String,Object> params);
}