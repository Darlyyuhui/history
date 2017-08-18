package com.xiangxun.ywpt.mobile.business.service;

import java.util.List;

import com.xiangxun.ywpt.mobile.business.domain.WorkorderInfo;
import com.xiangxun.ywpt.mobile.business.util.Page;




/**
 * 工单管理业务逻辑接口
 * @author miaoxu
 *
 */
public interface WorkorderInfoService {
	
	
	Page queryList(WorkorderInfo workorderInfo, int pageSize, int pageNo);
	/*List<WorkorderInfo> findAll();
	
	List<WorkorderInfo> findByType(String type);
	
	List<WorkorderInfo> findByDeviceCode(String devicecode);
	
	int countByContact(String contact);
	
	List getWorkorderCountByStatus();
	
	List getWorkorderCountByType();*/
	
	//查询已派发工单总条数
	int searchTotalCount(WorkorderInfo workorderInfo);
	
	//app端查看工单图片
	WorkorderInfo watchPicture(WorkorderInfo workorderInfo);
	
	//APP端滚动功能-查询已派发工单
	List<WorkorderInfo> totalWorkOrder(WorkorderInfo workorderInfo);

}
