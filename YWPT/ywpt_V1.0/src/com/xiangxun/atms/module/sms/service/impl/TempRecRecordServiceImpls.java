package com.xiangxun.atms.module.sms.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.sms.dao.TempRecRecordMapper;
import com.xiangxun.atms.module.sms.service.TempRecRecordService;
import com.xiangxun.atms.module.sms.vo.TempRecRecord;
import com.xiangxun.atms.module.sms.vo.TempRecRecordSearch;
/**
 * 短信临时接收表实现类
 * @author tianbo
 *
 */
@Service("tempRecRecordService")
public class TempRecRecordServiceImpls extends AbstractBaseService<TempRecRecord,TempRecRecordSearch> implements TempRecRecordService {
	
	@Resource
	TempRecRecordMapper tempRecRecordMapper;
	
	@Override
	protected BaseMapper<TempRecRecord, TempRecRecordSearch> getBaseMapper() {
		// TODO Auto-generated method stub
		return tempRecRecordMapper;
	}

}
