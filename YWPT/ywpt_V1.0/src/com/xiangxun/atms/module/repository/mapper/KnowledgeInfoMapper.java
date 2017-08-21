package com.xiangxun.atms.module.repository.mapper;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.repository.domain.KnowledgeInfo;
import com.xiangxun.atms.module.repository.domain.KnowledgeInfoSearch;

public interface KnowledgeInfoMapper extends BaseMapper<KnowledgeInfo, KnowledgeInfoSearch> {
	
	List selectKnowledgeByType(Map<String, Object> params);
	
	List selectKnowledgeByStatus();
}