package com.xiangxun.atms.common.log.dao;

import com.xiangxun.atms.common.log.vo.SystemLog;
import com.xiangxun.atms.common.log.vo.SystemLogSearch;
import com.xiangxun.atms.framework.base.BaseMapper;
/***
 * 系统日志
 * @author zhouhaij
 * @Apr 25, 2013 8:43:48 AM
 */
public interface SystemLogMapper extends BaseMapper<SystemLog, SystemLogSearch>{

	/***
	 * 获取上一次登录的信息
	 * @param account
	 * @param logTime
	 * @return
	 */
	SystemLog selectLastLogUser(String account,String logTime);
}