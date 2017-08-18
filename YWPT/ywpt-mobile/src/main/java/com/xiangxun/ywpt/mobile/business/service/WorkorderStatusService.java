package com.xiangxun.ywpt.mobile.business.service;

import com.xiangxun.ywpt.mobile.business.domain.WorkorderInfo;



/**
 * app端运维人员更改工单状态接口
 * @author miaoxu
 *
 */
public interface WorkorderStatusService {

	void changeWorkorderStatus(WorkorderInfo workorderInfo);

	void upLoadPicture(WorkorderInfo workorderInfo);
}
