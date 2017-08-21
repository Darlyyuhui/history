package com.xiangxun.atms.common.system.service.impl;



import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.common.system.dao.RetrieveInfoMapper;
import com.xiangxun.atms.common.system.service.RetrieveService;
import com.xiangxun.atms.common.system.vo.RetrieveInfo;
import com.xiangxun.atms.common.system.vo.RetrieveInfoSearch;
import com.xiangxun.atms.common.system.vo.RetrieveInfoSearch.Criteria;
import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.constant.FORMAT;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.framework.util.StringUtils;


/***
 * 系统回收站相关 服务接口类
 * @author yantao
 */

@Service("retrieveService")
public class RetrieveServiceImpl extends AbstractBaseService<RetrieveInfo, RetrieveInfoSearch> implements RetrieveService {

	@Resource
	RetrieveInfoMapper retrieveInfoMapper;
	
	@Override
	protected BaseMapper<RetrieveInfo, RetrieveInfoSearch> getBaseMapper() {
		return retrieveInfoMapper;
	}
	
	
	@Override
	public Page getByCondition(Map<String, Object> map,int pageNo,int pageSize, String sortType) {
		RetrieveInfoSearch search = new RetrieveInfoSearch();
		Criteria criteria = search.createCriteria();
		if(map!=null){
			if(StringUtils.notEmpty(map.get("operator")+"")){
				criteria.andOperatorLike("%"+map.get("operator").toString()+"%");
			}
			if(StringUtils.notEmpty(map.get("operatorip")+"")){
				criteria.andOperatoripLike("%"+map.get("operatorip").toString()+"%");
			}
			if(StringUtils.notEmpty(map.get("startTime")+"")){
				try {
					criteria.andDeltimeGreaterThanOrEqualTo(DateUtil.parseDate(map.get("startTime").toString(),FORMAT.DATE.DEFAULT));
				} catch (ParseException e) {
					logger.error(e);
				}
			}
			if(StringUtils.notEmpty(map.get("endTime")+"")){
				try {
					String tomorrowday = DateUtil.getTomorrowByDay(map.get("endTime").toString());
					criteria.andDeltimeLessThanOrEqualTo(DateUtil.parseDate(tomorrowday,FORMAT.DATE.DEFAULT));
				} catch (ParseException e) {
					logger.error(e);
				}
			}
		}
		if(StringUtils.notEmpty(sortType)){
			search.setOrderByClause(sortType);
		}
		Page page = selectByExample(search, pageNo, pageSize);
		return page;
	}
	
	@Override
	public List<RetrieveInfo> findAll() {
		RetrieveInfoSearch search = new RetrieveInfoSearch();
		search.createCriteria().andIdIsNotNull();
		return retrieveInfoMapper.selectByExample(search);
	}
	
	
}
