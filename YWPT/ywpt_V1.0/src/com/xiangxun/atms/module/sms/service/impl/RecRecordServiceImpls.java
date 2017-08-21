package com.xiangxun.atms.module.sms.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.sms.dao.RecRecordMapper;
import com.xiangxun.atms.module.sms.service.RecRecordService;
import com.xiangxun.atms.module.sms.vo.RecRecord;
import com.xiangxun.atms.module.sms.vo.RecRecordSearch;

/**
 * 短信日志实现类
 * @author tianbo
 *
 */
@Service("recRecordService")
public class RecRecordServiceImpls extends AbstractBaseService<RecRecord, RecRecordSearch> implements RecRecordService {
	
	@Resource
	RecRecordMapper recordMapper;
	
	@Override
	protected BaseMapper<RecRecord, RecRecordSearch> getBaseMapper() {
		// TODO Auto-generated method stub
		return recordMapper;
	}

}
