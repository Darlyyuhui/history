package com.xiangxun.atms.module.property.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.property.domain.ModifyRecord;
import com.xiangxun.atms.module.property.domain.ModifyRecordSearch;
import com.xiangxun.atms.module.property.mapper.ModifyRecordMapper;
import com.xiangxun.atms.module.property.service.ModifyRecordService;

@Service("modifyRecordService")
public class ModifyRecordServiceImpl extends AbstractBaseService<ModifyRecord, ModifyRecordSearch> implements ModifyRecordService {

	@Resource
	ModifyRecordMapper modifyRecordMapper;
	
	@Override
	protected BaseMapper<ModifyRecord, ModifyRecordSearch> getBaseMapper() {
		return modifyRecordMapper;
	}

	@Override
	public List<ModifyRecord> findByModifyId(String modifyId) {
		ModifyRecordSearch example = new ModifyRecordSearch();
		example.createCriteria().andModifyIdEqualTo(modifyId);
		return modifyRecordMapper.selectByExample(example);
	}

	@Override
	public List<ModifyRecord> findByMoudleName(String moduleName) {
		ModifyRecordSearch example = new ModifyRecordSearch();
		example.createCriteria().andModuleNameEqualTo(moduleName);
		return modifyRecordMapper.selectByExample(example);
	}

}
