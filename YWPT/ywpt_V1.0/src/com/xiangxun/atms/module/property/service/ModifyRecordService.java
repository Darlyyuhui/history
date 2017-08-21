package com.xiangxun.atms.module.property.service;

import java.util.List;

import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.module.property.domain.ModifyRecord;
import com.xiangxun.atms.module.property.domain.ModifyRecordSearch;

/**
 * 变更记录业务逻辑接口
 * @author kouyunhao
 *
 */
public interface ModifyRecordService extends BaseService<ModifyRecord, ModifyRecordSearch> {
	
	List<ModifyRecord> findByModifyId(String modifyId);
	
	List<ModifyRecord> findByMoudleName(String moduleName);

}
