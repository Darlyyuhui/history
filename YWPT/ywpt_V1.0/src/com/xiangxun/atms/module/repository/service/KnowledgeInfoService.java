package com.xiangxun.atms.module.repository.service;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.module.repository.domain.KnowledgeInfo;
import com.xiangxun.atms.module.repository.domain.KnowledgeInfoSearch;

/**
 * 知识库管理业务逻辑接口
 * @author kouyunhao
 *
 */
public interface KnowledgeInfoService extends BaseService<KnowledgeInfo, KnowledgeInfoSearch> {
	
	List<KnowledgeInfo> findAll();
	
	List<KnowledgeInfo> findKnowledgeByName(String name);
	
	List<KnowledgeInfo> findKnowledgeByWorkId(String workorderid);
	
	public boolean isContainsFile(String id);
	
	public List getKnowledgeByType(Map<String, Object> params);
	
	public List getKnowledgeByStatus();

}
