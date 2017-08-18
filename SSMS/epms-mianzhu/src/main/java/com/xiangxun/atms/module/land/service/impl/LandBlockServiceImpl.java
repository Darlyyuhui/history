package com.xiangxun.atms.module.land.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.module.land.cache.LandBlockCache;
import com.xiangxun.atms.module.land.dao.LandBlockMapper;
import com.xiangxun.atms.module.land.service.LandBlockService;
import com.xiangxun.atms.module.land.vo.LandBlock;
import com.xiangxun.atms.module.land.vo.LandBlockSearch;

@Service
public class LandBlockServiceImpl extends AbstractBaseService<LandBlock, LandBlockSearch> implements LandBlockService {
    @Resource
    private LandBlockMapper landBlockMapper;
    
    @Resource
    Cache cache;
    
    @Override
    public BaseMapper<LandBlock, LandBlockSearch> getBaseMapper() {
         return landBlockMapper;
    }

	@Override
	public List<LandBlock> queryBySelectItem() {
		return landBlockMapper.queryBySelectItem();
	}

	@Override
	public int deleteById(String id) {
		int isOK = super.deleteById(id);
		this.refreshCache();
		return isOK;
	}

	@Override
	public int save(LandBlock record) {
		int isOK = super.save(record);
		this.refreshCache();
		return isOK;
	}

	@Override
	public int updateByIdSelective(LandBlock record) {
		int isOK = super.updateByIdSelective(record);
		this.refreshCache();
		return isOK;
	}

	@Override
	public void refreshCache() {
		LandBlockSearch search = new LandBlockSearch();
		search.setOrderByClause("TYPE_CODE ASC, CREATE_TIME DESC");
		
		List<LandBlock> list = this.selectByExample(search);
		
		Table<String, String, LandBlock> table = HashBasedTable.create();
		Table<String, String, String> table2 = HashBasedTable.create();
		Table<String, String, String> table3 = HashBasedTable.create();
		Table<String, String, String> table4 = HashBasedTable.create();
        for (LandBlock lb : list) {
            table.put(lb.getId(), LandBlockCache.LB_ID_OBJ, lb);
            table2.put(lb.getId(), LandBlockCache.LB_ID_CODENAME, lb.getName()+"【"+lb.getCode()+"】");
            table3.put(lb.getId(), LandBlockCache.LB_ID_NAME, lb.getName());
            table4.put(lb.getId(), LandBlockCache.LB_ID_CODE, lb.getCode());
        }
        cache.put(LandBlockCache.LB_ALL, list);
        cache.put(LandBlockCache.LB_ID_OBJ, table);
        cache.put(LandBlockCache.LB_ID_CODENAME, table2);
        cache.put(LandBlockCache.LB_ID_NAME, table3);
        cache.put(LandBlockCache.LB_ID_CODE, table4);
	}
	
}