package com.xiangxun.atms.module.pollute.dao;

import java.util.List;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.pollute.vo.PolluteCompany;
import com.xiangxun.atms.module.pollute.vo.PolluteCompanySearch;

public interface PolluteCompanyMapper extends BaseMapper<PolluteCompany, PolluteCompanySearch> {
	
	List<PolluteCompany> selectAll();
}