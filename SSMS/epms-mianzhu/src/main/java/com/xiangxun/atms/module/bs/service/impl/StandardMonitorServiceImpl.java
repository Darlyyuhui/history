package com.xiangxun.atms.module.bs.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.module.bs.cache.StandardMonitorCahe;
import com.xiangxun.atms.module.bs.dao.StandardMonitorMapper;
import com.xiangxun.atms.module.bs.service.StandardMonitorService;
import com.xiangxun.atms.module.bs.vo.StandardMonitor;
import com.xiangxun.atms.module.bs.vo.StandardMonitorSearch;

@Service
public class StandardMonitorServiceImpl extends AbstractBaseService<StandardMonitor, StandardMonitorSearch> implements StandardMonitorService {
    @Resource
    private StandardMonitorMapper standardMonitorMapper;
    @Resource
    Cache cache;
    
    @Override
    public BaseMapper<StandardMonitor, StandardMonitorSearch> getBaseMapper() {
         return standardMonitorMapper;
    }

	@Override
	public int deleteById(String id) {
		int isOK = super.deleteById(id);
		this.refreshCache();
		return isOK;
		
	}

	@Override
	public int save(StandardMonitor record) {
		int isOK = super.save(record);
		this.refreshCache();
		return isOK;
	}

	@Override
	public int updateByIdSelective(StandardMonitor record) {
		int isOK = super.updateByIdSelective(record);
		this.refreshCache();
		return isOK;
	}

	@Override
	public void refreshCache() {
		StandardMonitorSearch search = new StandardMonitorSearch();
		search.setOrderByClause("CODE ASC");
		List<StandardMonitor> list = this.selectByExample(search);
		//存放相同类型的指标
		Map<String, List<StandardMonitor>> map = new HashMap<String, List<StandardMonitor>>();
		List<StandardMonitor> temp = null;
		//根据ID获取对象
		Table<String, String, StandardMonitor> table2 = HashBasedTable.create();
        for (StandardMonitor sm : list) {
            table2.put(sm.getId(), StandardMonitorCahe.ID_OBJ, sm);
            temp = map.get(sm.getTypeCode());
            if (temp == null) {
            	temp = new ArrayList<StandardMonitor>();
            }
            temp.add(sm);
            map.put(sm.getTypeCode(), temp);
        }
        cache.put(StandardMonitorCahe.ID_OBJ, table2);
        cache.put(StandardMonitorCahe.TYPE_ITEMS, map);
	}
		
	
}