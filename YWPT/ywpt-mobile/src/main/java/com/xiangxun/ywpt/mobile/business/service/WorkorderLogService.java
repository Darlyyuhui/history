package com.xiangxun.ywpt.mobile.business.service;

import com.xiangxun.ywpt.mobile.business.domain.WorkorderLog;

/**
 * 工单操作日志业务逻辑接口
 * @author yangzhenyu
 *
 */
public interface WorkorderLogService {
   void save(WorkorderLog log);
}
