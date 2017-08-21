package com.xiangxun.atms.common.log.service;

import java.util.Map;

import com.xiangxun.atms.common.log.vo.OperationLog;
import com.xiangxun.atms.framework.base.Page;

/***
 * 日志服务类
 * @author zhouhaij
 * @Apr 25, 2013 8:23:59 AM
 */
public interface OperateLogService {

	/***
	 * 保存日志
	 * @param log
	 */
	public void save(OperationLog log);
	
	
	/***
	 * 根据id获取操作日志
	 * @param id
	 * @return
	 */
    public OperationLog selectLogById(String id);
    
    /**
     * 根据ID删除操作日志
     * @param id
     * @return
     */
	public void deleteLogById(String id);
	
	/***
	 * 获取日志的分页列表数据
	 * @param orgid
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page getLogsByCondition(Map<String, Object> map,int pageNo,int pageSize,String sortType);
	
	
	 /**
     * 按操作人员统计违法确认操作日志
     * @param map
     * @param pageNo
     * @param pageSize
     * @param sortType
     * @return
     */
    public Page getLogsStatis(Map<String, Object> map, int pageNo, int pageSize);
}
