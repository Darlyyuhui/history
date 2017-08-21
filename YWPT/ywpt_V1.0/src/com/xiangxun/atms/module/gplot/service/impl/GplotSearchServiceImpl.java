
package com.xiangxun.atms.module.gplot.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.common.user.service.AuthorityFilterContext;
import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.module.gplot.domain.StatusApp;
import com.xiangxun.atms.module.gplot.domain.StatusAppSearch;
import com.xiangxun.atms.module.gplot.domain.StatusAppSearch.Criteria;
import com.xiangxun.atms.module.gplot.mapper.StatusAppMapper;
import com.xiangxun.atms.module.gplot.service.GplotSearchService;
/**
 * 设备拓扑图服务
 * @author miaoxu
 * @version 1.0
 */
@Service("GplotSearchService")
public class GplotSearchServiceImpl extends AbstractBaseService <StatusApp,StatusAppSearch> implements GplotSearchService {

	@Resource
	StatusAppMapper statusAppMapper;
	
	@Resource(name="authorityFilterContext")
	AuthorityFilterContext authorityFilterContext;
	
	@Override
	protected BaseMapper<StatusApp, StatusAppSearch> getBaseMapper() {
		// TODO Auto-generated method stub
		return statusAppMapper;
	}

	/***
	 * 获取根据字段TYPE分类的数据
	 * @param searchParams
	 * @return statusApp
	 */
	@Override
	public List<StatusApp> getTypeByCondition(Map<String, String> searchParams) {
	
		StatusAppSearch search =new  StatusAppSearch();
		Criteria criteria = search.createCriteria();
 		List<StatusApp> statusApp = null;
 		
 		if(searchParams == null){
			statusApp= selectByExample(search);		
		}
		else{
			
			criteria.andTypeEqualTo(searchParams.get("type"));
			statusApp=selectByExample(search);
		}
 		
		return statusApp;
	}

}
