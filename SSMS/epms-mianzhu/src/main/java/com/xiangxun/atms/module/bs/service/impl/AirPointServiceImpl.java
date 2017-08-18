package com.xiangxun.atms.module.bs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.module.bs.cache.AirPointCache;
import com.xiangxun.atms.module.bs.dao.AirPointMapper;
import com.xiangxun.atms.module.bs.service.AirPointService;
import com.xiangxun.atms.module.bs.vo.AirPoint;
import com.xiangxun.atms.module.bs.vo.AirPointSearch;

@Service
public class AirPointServiceImpl extends AbstractBaseService<AirPoint, AirPointSearch> implements AirPointService {
    @Resource
    private AirPointMapper airPointMapper;
    @Resource
    Cache cache;
    
    @Override
    public BaseMapper<AirPoint, AirPointSearch> getBaseMapper() {
         return airPointMapper;
    }
    
	@Override
	public int deleteById(String id) {
		int isOK = super.deleteById(id);
		this.refreshCache();
		return isOK;
	}

	@Override
	public int save(AirPoint record) {
		int isOK = super.save(record);
		this.refreshCache();
		return isOK;
	}

	@Override
	public int updateByIdSelective(AirPoint record) {
		int isOK = super.updateByIdSelective(record);
		this.refreshCache();
		return isOK;
	}
	
	@Override
	public void refreshCache() {
		AirPointSearch search = new AirPointSearch();
		search.setOrderByClause("CODE");
		List<AirPoint> list = this.selectByExample(search);
		
		Table<String, String, String> table = HashBasedTable.create();
		Table<String, String, String> table2 = HashBasedTable.create();
		Table<String, String, AirPoint> table3 = HashBasedTable.create();
		for (AirPoint ap : list) {
			table.put(ap.getId(), AirPointCache.ID_NAME, ap.getName());
			table2.put(ap.getId(), AirPointCache.ID_LOCATION, ap.getLongitude()+","+ap.getLatitude());
			table3.put(ap.getId(), AirPointCache.ID_OBJ, ap);
		}
		cache.put(AirPointCache.ALL_ITEM, list);
		cache.put(AirPointCache.ID_NAME, table);
		cache.put(AirPointCache.ID_LOCATION, table2);
		cache.put(AirPointCache.ID_OBJ, table3);
	}
    
}