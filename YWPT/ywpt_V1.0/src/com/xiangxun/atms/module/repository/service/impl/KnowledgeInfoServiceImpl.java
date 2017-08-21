package com.xiangxun.atms.module.repository.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.repository.domain.KnowledgeInfo;
import com.xiangxun.atms.module.repository.domain.KnowledgeInfoSearch;
import com.xiangxun.atms.module.repository.mapper.KnowledgeInfoMapper;
import com.xiangxun.atms.module.repository.service.KnowledgeInfoService;

/**
 * 知识库管理业务逻辑接口实现类
 * @author kouyunhao
 *
 */
@Service("knowledgeInfoService")
public class KnowledgeInfoServiceImpl extends AbstractBaseService<KnowledgeInfo, KnowledgeInfoSearch> implements KnowledgeInfoService {
	
	@Resource
	KnowledgeInfoMapper knowledgeInfoMapper;
	
	@Override
	protected BaseMapper<KnowledgeInfo, KnowledgeInfoSearch> getBaseMapper() {
		return knowledgeInfoMapper;
	}

	@Override
	public List<KnowledgeInfo> findAll() {
		KnowledgeInfoSearch example = new KnowledgeInfoSearch();
		example.createCriteria().andIdIsNotNull();
		return knowledgeInfoMapper.selectByExample(example);
	}

	@Override
	public List<KnowledgeInfo> findKnowledgeByName(String name) {
		KnowledgeInfoSearch example = new KnowledgeInfoSearch();
		example.createCriteria().andIdIsNotNull().andNameEqualTo(name);
		return knowledgeInfoMapper.selectByExample(example);
	}

	@Override
	public List<KnowledgeInfo> findKnowledgeByWorkId(String workorderid) {
		KnowledgeInfoSearch example = new KnowledgeInfoSearch();
		example.createCriteria().andIdIsNotNull().andNoteEqualTo(workorderid);
		return knowledgeInfoMapper.selectByExample(example);
	}
	
	/* (non-Javadoc)
	 * @see@see com.xiangxun.atms.module.emergency.service.DocFileService#isContainsFile(java.lang.String)
	 */
	@Override
	public boolean isContainsFile(String id) {
		KnowledgeInfoSearch example = new KnowledgeInfoSearch();
		example.createCriteria().andIdIsNotNull().andPidEqualTo(id);
		List<KnowledgeInfo> fileList = knowledgeInfoMapper.selectByExample(example);
		if(fileList != null && fileList.size() != 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List getKnowledgeByType(Map<String, Object> params){
		return knowledgeInfoMapper.selectKnowledgeByType(params);
	}

	@Override
	public List getKnowledgeByStatus() {
		return knowledgeInfoMapper.selectKnowledgeByStatus();
	}

}
