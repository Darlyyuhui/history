package com.xiangxun.atms.module.eventalarm.service;

import java.util.Date;
import java.util.List;

import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderInfo;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderInfoSearch;

/**
 * 工单管理业务逻辑接口
 * @author kouyunhao
 *
 */
public interface WorkorderInfoService extends BaseService<WorkorderInfo, WorkorderInfoSearch> {
	
	List<WorkorderInfo> findAll();
	
	List<WorkorderInfo> findByType(String type);
	
	List<WorkorderInfo> findByDeviceCode(String devicecode);
	
	int countByContact(String contact);
	
	List getWorkorderCountByStatus();
	
	List getWorkorderCountByType();
	
	boolean xunGengWordOrder(String ip,String deviceType,Date xungengTime);

}
