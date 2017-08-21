package com.xiangxun.atms.module.eventalarm.job;


import javax.annotation.Resource;

import com.xiangxun.atms.framework.log.Logging;
import com.xiangxun.atms.module.eventalarm.mapper.AlarmLogMapper;


/**
 * 删除运维STATUS表历史数据
 * @author yantao
 */
public class DeleteStatusDataJob{
	
	@Resource
	AlarmLogMapper alarmLogMapper;
	
	protected Logging logger = new Logging(getClass());
	
	
	public void process() {
		try{
			logger.info("1......");
			
			alarmLogMapper.deleteStatusByJob();
			
			logger.info("2......");
		}catch(Exception e){
			logger.error(e);
		}
		
	}

}
