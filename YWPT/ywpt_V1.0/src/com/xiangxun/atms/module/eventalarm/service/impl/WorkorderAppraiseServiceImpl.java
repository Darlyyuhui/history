package com.xiangxun.atms.module.eventalarm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderAppraise;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderAppraiseSearch;
import com.xiangxun.atms.module.eventalarm.mapper.WorkorderAppraiseMapper;
import com.xiangxun.atms.module.eventalarm.service.WorkorderAppraiseService;

/**
 * 工单运维评估业务逻辑接口实现类
 * @author kouyunhao
 *
 */
@Service("workorderAppraiseService")
public class WorkorderAppraiseServiceImpl extends AbstractBaseService<WorkorderAppraise, WorkorderAppraiseSearch> implements WorkorderAppraiseService {

	@Resource
	WorkorderAppraiseMapper workorderAppraiseMapper;
	
	@Override
	protected BaseMapper<WorkorderAppraise, WorkorderAppraiseSearch> getBaseMapper() {
		return workorderAppraiseMapper;
	}

	@Override
	public List<WorkorderAppraise> findByWorkorderId(String workorderid) {
		WorkorderAppraiseSearch example = new WorkorderAppraiseSearch();
		example.createCriteria().andWorkorderidEqualTo(workorderid);
		return workorderAppraiseMapper.selectByExample(example);
	}

	@Override
	public int findAvgFinalscore(String companyid) {
		return workorderAppraiseMapper.findAvgFinalscore(companyid);
	}

}
