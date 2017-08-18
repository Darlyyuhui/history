package com.xiangxun.atms.module.bs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.module.bs.cache.OwnerCahe;
import com.xiangxun.atms.module.bs.dao.OwnerMapper;
import com.xiangxun.atms.module.bs.service.OwnerService;
import com.xiangxun.atms.module.bs.vo.Owner;
import com.xiangxun.atms.module.bs.vo.OwnerSearch;


@Service
public class OwnerServiceImpl extends AbstractBaseService<Owner, OwnerSearch> implements OwnerService {
    @Resource
    private OwnerMapper ownerMapper;
    @Resource
    Cache cache;
    @Override
    public BaseMapper<Owner, OwnerSearch> getBaseMapper() {
         return ownerMapper;
    }

	@Override
	public void refreshCache() {
		OwnerSearch search = new OwnerSearch();
		search.setOrderByClause("ID ASC");
		List< Owner> list = this.selectByExample(search);
		Table<String, String, String> table = HashBasedTable.create();
        for ( Owner lt : list) {
            table.put(lt.getId(), OwnerCahe.ID_NAME, lt.getName());
        }
        cache.put(OwnerCahe.ID_NAME,table);
        cache.put(OwnerCahe.ALL_ITEM, list);
	}

	@Override
	public int deleteById(String id) {
		int isOK = super.deleteById(id);
		this.refreshCache();
		return isOK;
	}

	@Override
	public int save(Owner record) {
		int isOK = super.save(record);
		this.refreshCache();
		return isOK;
	}

	@Override
	public int updateByIdSelective(Owner record) {
		int isOK = super.updateByIdSelective(record);
		this.refreshCache();
		return isOK;
	}
		
	
}