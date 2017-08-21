package com.xiangxun.atms.module.eventalarm.service;

import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderInfo;

/**
 * 工单管理公用方法
 * @author kouyunhao
 *
 */
public interface PublicMethodService {
	
	public void setStatusColor(Page page);
	
	public void setStatusColor(WorkorderInfo workorder);

}
