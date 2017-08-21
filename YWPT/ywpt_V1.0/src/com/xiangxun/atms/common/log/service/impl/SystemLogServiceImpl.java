package com.xiangxun.atms.common.log.service.impl;

import java.text.ParseException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiangxun.atms.common.log.dao.SystemLogMapper;
import com.xiangxun.atms.common.log.service.SystemLogService;
import com.xiangxun.atms.common.log.vo.SystemLog;
import com.xiangxun.atms.common.log.vo.SystemLogSearch;
import com.xiangxun.atms.common.log.vo.SystemLogSearch.Criteria;
import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.constant.FORMAT;
import com.xiangxun.atms.framework.security.OperatorDetails;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.framework.util.StringUtils;

/***
 * 登录日志服务接口
 * @author zhouhaij
 * @Apr 28, 2013 9:08:37 AM
 */
@Service
public class SystemLogServiceImpl extends AbstractBaseService<SystemLog,SystemLogSearch>implements SystemLogService {

	@Resource
	SystemLogMapper systemLogMapper;
	
	@Override
	@Transactional
	public int save(SystemLog log) {
		return systemLogMapper.insertSelective(log);
	}

	public SystemLog getById(String id){
		SystemLogSearch search = new SystemLogSearch();
		Criteria criteria = search.createCriteria();
		criteria.andIdEqualTo(id);
		return selectByExample(search).get(0);
	}
	
	
	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.log.service.SystemLogService#getSystemLogsByCondition(java.util.Map, int, int, java.lang.String)
	 */
	@Override
	public Page getSystemLogsByCondition(Map<String, Object> map, int pageNo, int pageSize, String sortType) {
		SystemLogSearch search = new SystemLogSearch();
		Criteria criteria = search.createCriteria();
		if(map!=null){
			if(StringUtils.notEmpty(map.get("startTime")+"")){
				try {
					criteria.andOperationTimeGreaterThanOrEqualTo(DateUtil.parseDate(map.get("startTime").toString(),FORMAT.DATETIME.DEFAULT));
				} catch (ParseException e) {
					logger.error(e);
				}
			}
			
			if(StringUtils.notEmpty(map.get("endtime")+"")){
				try {
					criteria.andOperationTimeLessThanOrEqualTo(DateUtil.parseDate(map.get("endtime").toString(),FORMAT.DATETIME.DEFAULT));
				} catch (ParseException e) {
					logger.error(e);
				}
			}
			
			if(StringUtils.notEmpty(map.get("type")+"")){
				criteria.andTypeEqualTo(Long.parseLong(map.get("type")+""));
			}
			
			if(StringUtils.notEmpty(map.get("operatorId")+"")){
				criteria.andOperatorIdEqualTo(map.get("operatorId")+"");
			}
			
			if(StringUtils.notEmpty(map.get("operatorName")+"")){
				criteria.andOperatorNameLike("%"+map.get("operatorName")+"%");
			}
		}
		search.setOrderByClause("OPERATION_TIME desc");
		Page page = selectByExample(search, pageNo, pageSize);
		return page;
	}
	
	
	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.log.service.SystemLogService#getLastLogInfo(com.xiangxun.atms.framework.security.OperatorDetails)
	 */
	@Override
	public SystemLog getLastLogInfo(OperatorDetails operator) {
		return systemLogMapper.selectLastLogUser(operator.getAccount(),DateUtil.formatDate(com.xiangxun.atms.framework.constant.FORMAT.DATETIME.DEFAULT,operator.getLoginTime()));
	}


	/* (non-Javadoc)
	 * @see com.xiangxun.atms.framework.base.AbstractBaseService#getBaseMapper()
	 */
	@Override
	protected BaseMapper<SystemLog, SystemLogSearch> getBaseMapper() {
		return systemLogMapper;
	}

	
}
