package com.xiangxun.atms.module.eventalarm.service;

import java.util.List;

import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderAppraise;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderAppraiseSearch;

/**
 * 工单运维评估业务逻辑接口
 * @author kouyunhao
 *
 */
public interface WorkorderAppraiseService extends BaseService<WorkorderAppraise, WorkorderAppraiseSearch> {
	
	List<WorkorderAppraise> findByWorkorderId(String workorderid);
	
	//获取此id服务商所有评论的平均数
	int findAvgFinalscore(String companyid);

}
