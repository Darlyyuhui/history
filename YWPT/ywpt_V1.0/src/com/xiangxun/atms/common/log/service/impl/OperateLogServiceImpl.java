package com.xiangxun.atms.common.log.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.stereotype.Service;

import com.xiangxun.atms.common.log.dao.OperateLogMapper;
import com.xiangxun.atms.common.log.service.OperateLogService;
import com.xiangxun.atms.common.log.vo.OperationLog;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.util.StringUtils;

/**
 * *
 * 日志服务类
 *
 * @author zhouhaij
 * @param <OperationLogSearch>
 * @Apr 25, 2013 8:23:59 AM
 */
@Service
public class OperateLogServiceImpl implements OperateLogService {

    @Resource
    OperateLogMapper operateLogMapper;

    /**
     * *
     * 保存日志
     *
     * @param log
     */
    @Override
    public void save(OperationLog log) {
        operateLogMapper.insert(log);
    }

    /**
     * *
     * 获取日志的分页列表数据
     *
     * @param orgid
     * @param pageNo
     * @param pageSize
     * @return
     * @throws  
     */
    @Override
    public Page getLogsByCondition(Map<String, Object> map, int pageNo, int pageSize, String sortType)   {
        OperationLog operationLog = new OperationLog();
        try {
            ConvertUtils.register(new DateConverter(), Date.class);
            // 将查询的map转换成对象
            BeanUtils.populate(operationLog, map);
        } catch ( IllegalAccessException e) {
        } catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        if (StringUtils.isEmpty(operationLog.getOperator())) {
            operationLog.setOperator(null);
        }

        if (StringUtils.isEmpty(operationLog.getStartTime())) {
            operationLog.setStartTime(null);
        }

        if (StringUtils.isEmpty(operationLog.getEndtime())) {
            operationLog.setEndtime(null);
        }

        if (StringUtils.isEmpty(operationLog.getIp())) {
            operationLog.setIp(null);
        }

        if (StringUtils.isEmpty(operationLog.getIsSuccess())) {
            operationLog.setIsSuccess(null);
        }
        List<OperationLog> results = operateLogMapper.selectLogs(operationLog, Page.getRowBounds(pageNo, pageSize));
        int totalCount = operateLogMapper.countByExample(operationLog);
        return Page.getPage(totalCount, results, pageNo, pageSize);
    }

    /**
     * *
     * 根据id获取操作日志
     *
     * @param id
     * @return
     */
    @Override
    public OperationLog selectLogById(String id) {
        return operateLogMapper.selectLogById(id);
    }
    /**
     * 根据ID删除操作日志
     * @param id
     * @return
     */
    @Override
	public void deleteLogById(String id) {
		operateLogMapper.deleteLogById(id);
	}
    
    /**
     * 按操作人员统计违法确认操作日志
     * @param map
     * @param pageNo
     * @param pageSize
     * @param sortType
     * @return
     */
    @Override
    public Page getLogsStatis(Map<String, Object> map, int pageNo, int pageSize)   {
    	OperationLog operationLog = new OperationLog();
        try {
            ConvertUtils.register(new DateConverter(), Date.class);
            // 将查询的map转换成对象
            BeanUtils.populate(operationLog, map);
        } catch ( IllegalAccessException e) {
        } catch (InvocationTargetException e) {
			e.printStackTrace();
		}

        if (StringUtils.isEmpty(operationLog.getStartTime())) {
            operationLog.setStartTime(null);
        }

        if (StringUtils.isEmpty(operationLog.getEndtime())) {
            operationLog.setEndtime(null);
        }
        List<OperationLog> results = operateLogMapper.selectLogStatis(operationLog, Page.getRowBounds(pageNo, pageSize));
		int totalCount = operateLogMapper.selectTotalLogStatis(operationLog);
        return Page.getPage(totalCount, results, pageNo, pageSize);
    }
}
