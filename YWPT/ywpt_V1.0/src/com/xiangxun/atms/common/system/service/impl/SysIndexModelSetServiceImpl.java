package com.xiangxun.atms.common.system.service.impl;



import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.common.system.dao.SysIndexModelSetMapper;
import com.xiangxun.atms.common.system.service.SysIndexModelSetService;
import com.xiangxun.atms.common.system.vo.SysIndexModelSet;
import com.xiangxun.atms.common.system.vo.SysIndexModelSetSearch;
import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;




@Service("sysIndexModelSetService")
public class SysIndexModelSetServiceImpl extends AbstractBaseService<SysIndexModelSet, SysIndexModelSetSearch> implements SysIndexModelSetService {
	
	@Resource
	SysIndexModelSetMapper sysIndexModelSetMapper;

	@Override
	protected BaseMapper<SysIndexModelSet, SysIndexModelSetSearch> getBaseMapper() {
		return sysIndexModelSetMapper;
	}

	@Override
	public SysIndexModelSet selectByUserId(String userid) {
		return sysIndexModelSetMapper.selectByUserId(userid);
	}


	
}
