package com.xiangxun.atms.module.sms.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.sms.dao.SentRecordMapper;
import com.xiangxun.atms.module.sms.service.SentRecordService;
import com.xiangxun.atms.module.sms.vo.SentRecord;
import com.xiangxun.atms.module.sms.vo.SentRecordSearch;

/**
 * 短信已发送实现类
 * @author Administrator
 *
 */
@Service("sentRecordService")
public class SentRecordServiceImpls extends AbstractBaseService<SentRecord,SentRecordSearch> implements SentRecordService {
	@Resource
	SentRecordMapper sentRecordMapper;
	
	@Override
	protected BaseMapper<SentRecord, SentRecordSearch> getBaseMapper() {
		// TODO Auto-generated method stub
		return sentRecordMapper;
	}

}
