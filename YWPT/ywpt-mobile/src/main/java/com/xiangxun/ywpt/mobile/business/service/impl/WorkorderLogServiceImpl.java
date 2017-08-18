package com.xiangxun.ywpt.mobile.business.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.ywpt.mobile.business.dao.WorkorderLogMapper;
import com.xiangxun.ywpt.mobile.business.domain.WorkorderLog;
import com.xiangxun.ywpt.mobile.business.service.WorkorderLogService;


/**
 * 工单操作日志业务逻辑接口
 * @author yangzhenyu
 *
 */
@Service("workorderLogService")
public class WorkorderLogServiceImpl implements WorkorderLogService  {
	
@Resource WorkorderLogMapper workorderLogMapper;

     public void save(WorkorderLog log){
    	 workorderLogMapper.save(log);
     }
	

}
