package com.xiangxun.atms.common.log.service;

import java.util.Map;

import com.xiangxun.atms.common.log.vo.SystemLog;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.security.OperatorDetails;

/***
 * 登录日志服务接口
 * @author zhouhaij
 * @Apr 28, 2013 9:08:37 AM
 */
public interface SystemLogService {

	/***
	 * 数据保存
	 * @param log
	 */
	public int save(SystemLog log);
	
	/***
	 * 根据id获取详情信息
	 * @param id
	 * @return
	 */
	public SystemLog getById(String id);
	
	/***
	 * 获取登陆日志分页数据
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @param sortType
	 * @return
	 */
	public Page getSystemLogsByCondition(Map<String, Object> map,int pageNo,int pageSize,String sortType);
	
	/***
	 * 获取上次登录的相关信息
	 * @param operator
	 * @return
	 */
	public SystemLog getLastLogInfo(OperatorDetails operator);
}
