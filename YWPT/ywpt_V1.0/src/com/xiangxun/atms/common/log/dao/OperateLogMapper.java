package com.xiangxun.atms.common.log.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.xiangxun.atms.common.log.vo.OperationLog;

public interface OperateLogMapper {

	/***
	 * 保存日志
	 * @param log
	 */
	public void insert(OperationLog log);
	
	
	/***
	 * 获取日志的列表数据
	 * @param orgid
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
    List<OperationLog> selectLogs(OperationLog log,RowBounds rowBounds);
    
    
	/***
	 * 根据实例获取记录条数
	 * @param example
	 * @return
	 */
    int countByExample(OperationLog log);
	
    public OperationLog selectLogById(String id);
    
    /**
     * 根据ID删除操作日志
     * @param id
     */
    public void deleteLogById(String id);
    
    /**
     * 按操作人员统计违法确认操作日志
     * @param log
     * @param rowBounds
     * @return list
     */
    List<OperationLog> selectLogStatis(OperationLog log,RowBounds rowBounds);
    
    /***
	 * 获取分页总数
	 * @param log
	 * @return int
	 */
	int selectTotalLogStatis(OperationLog log);
}
