package com.xiangxun.atms.common.system.service.impl;



import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.common.system.dao.SysIndexModelMapper;
import com.xiangxun.atms.common.system.service.SysIndexModelService;
import com.xiangxun.atms.common.system.vo.SysIndexModel;
import com.xiangxun.atms.common.system.vo.SysIndexModelSearch;
import com.xiangxun.atms.common.system.vo.SysIndexModelSearch.Criteria;
import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.util.StringUtils;


/***
 * FTP相关的 服务接口类
 * @author yantao
 */

@Service("sysIndexModelService")
public class SysIndexModelServiceImpl extends AbstractBaseService<SysIndexModel, SysIndexModelSearch> implements SysIndexModelService {

	@Resource
	SysIndexModelMapper sysIndexModelMapper;
	
	
	
	@Override
	protected BaseMapper<SysIndexModel, SysIndexModelSearch> getBaseMapper() {
		return sysIndexModelMapper;
	}
	
	
	@Override
	public Page getByCondition(Map<String, Object> map,int pageNo,int pageSize, String sortType) {
		SysIndexModelSearch search = new SysIndexModelSearch();
		Criteria criteria = search.createCriteria();
		if(map!=null){
			if(StringUtils.notEmpty(map.get("name")+"")){
				criteria.andNameLike("%"+map.get("name").toString()+"%");
			}
		}
		if(StringUtils.notEmpty(sortType)){
			search.setOrderByClause(sortType);
		}
		Page page = selectByExample(search, pageNo, pageSize);
		return page;
	}



	@Override
	public List<SysIndexModel> findAll() {
		SysIndexModelSearch search = new SysIndexModelSearch();
		search.createCriteria().andIdIsNotNull();
		return sysIndexModelMapper.selectByExample(search);
	}


	@Override
	public SysIndexModel getByCode(String code) {
		return sysIndexModelMapper.getByCode(code);
	}


	@Override
	public List<SysIndexModel> selectByIsShow(String isshow) {
		return sysIndexModelMapper.selectByIsShow(isshow);
	}
	

	
}
